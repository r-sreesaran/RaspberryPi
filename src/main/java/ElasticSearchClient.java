/**
 * Created by sree on 21/05/16.
 */


import com.google.gson.*;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;


/**
 * Created by sree on 19/05/16.
 */
public class ElasticSearchClient {
    String promotionalCode="rv90";
    String responseData;
    String customerCellNo;
    public static final String ACCOUNT_SID = "AC23ed320178145271743a97cf7a63f2b9";
    public static final String AUTH_TOKEN = "6090858bb20af2ae43427dc5f68c9c20";
    /**
     * Retirves the customer details
     * @param uniqueID
     * @return
     * @throws UnknownHostException
     */
    public String geCustomerDetails(String uniqueID) throws UnknownHostException {
        responseData = given().baseUri("http://localhost:9200/mylapore/Invoice/"+uniqueID).contentType("application/json").get().body().asString();
        return responseData;
    }

    /**
     * This save the bill details to elastic search
     * @param data
     * @throws UnknownHostException
     */
    public void putCustomerDetails(Data data) throws UnknownHostException {
        Gson gson = new Gson();
        gson.toJson(data);
        given().baseUri("http://localhsot:9200").body(gson).with().contentType("application/json").post("/mylapore/Invoice/"+data.getDate()+"-"+data.getBillNumber());
    }


    /**
     * Add or update the customer rating
     * @param rating
     * @param uniqueId
     * @throws UnknownHostException
     */
    public void addRating(int rating,String uniqueId) throws UnknownHostException, TwilioRestException {
        given().baseUri("http://localhost:9200").body("{\n" +
                "   \"doc\" : {\n" +
                "      \"rating1\" :  "+rating+" \n" +
                "      \n" +
                "   }\n" +
                "}").contentType("application/json").post("/mylapore/Invoice/"+uniqueId+"/_update");
        generatePromoCode(rating,uniqueId);
    }

    /**
     * Generates the promotional code based on rating or the number of times the customer has given a rating
     * @param uniqueId
     */
    public void generatePromoCode(int rating,String uniqueId) throws TwilioRestException {
        String responseData = given().baseUri("http://localhost:9200").get("/mylapore/Invoice/"+uniqueId+"/_source").body().asString();
        JsonObject billDet = new JsonParser().parse(responseData).getAsJsonObject();
        String rat = billDet.get("rating").toString();
        JsonObject custDet = billDet.get("customerDetails").getAsJsonObject();
        customerCellNo = custDet.get("cell").toString();


        //Logic for obtaining based on cell number
        String countData =  given().baseUri("http://localhost:9200").body("{\n" +
                "        \"query\" : {\n" +
                "            \"term\" : { \"customerDetails.cell\":"+customerCellNo+"}\n" +
                "        }\n" +
                "    }").post("/mylapore/Invoice/_count").body().asString();
        String count = new JsonParser().parse(countData).getAsJsonObject().get("count").toString();

        if((rating<=2||Integer.parseInt(count)>4)&&rat==null){

           //This verify whether the field is present in elastic search

           //code logic for sending sms
           TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair("From", "+12282078733"));
           params.add(new BasicNameValuePair("To", "+91"+customerCellNo));
           params.add(new BasicNameValuePair("Body", "PromoCode"));

           MessageFactory messageFactory = client.getAccount().getMessageFactory();
           Message message = messageFactory.create(params);
           System.out.println(message.getSid());
           messageToManager();
       }
       else {
        // Display message for ui
       }


    }

    public void messageToManager() {

    }

    public static void main(String[] args) throws UnknownHostException, TwilioRestException {
        ElasticSearchClient client = new ElasticSearchClient();
        client.generatePromoCode(1,"2016-04-01_11");
        //client.geCustomerDetails("2016-04-01_11");
    }

}
