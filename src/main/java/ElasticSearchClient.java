/**
 * Created by sree on 21/05/16.
 */


import com.google.gson.*;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.ResponseSpecification;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;

import static com.jayway.restassured.http.ContentType.JSON;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static com.jayway.restassured.RestAssured.*;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by sree on 19/05/16.
 */
public class ElasticSearchClient {
    String promotionalCode="rv90";
    String responseData;
    String customerCellNo;
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
    public void addRating(int rating,String uniqueId) throws UnknownHostException {
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
    public void generatePromoCode(int rating,String uniqueId) {
       if(rating<=2){
          String responseData = given().baseUri("http://localhost:9200").get("/mylapore/Invoice/"+uniqueId).body().asString();
          JsonObject jsonObj = new JsonParser().parse(responseData).getAsJsonObject();
          JsonObject custDetails = jsonObj.get("CustomerDetails").getAsJsonObject();
          customerCellNo = custDetails.getAsString();

          //customerName = jsonObj.get("").toString();
          messageToManager();
       }
        else {

       }


    }



    public void messageToManager() {

    }

    public static void main(String[] args) throws UnknownHostException {
        ElasticSearchClient client = new ElasticSearchClient();
        client.generatePromoCode(1,"2016-04-01_11");
        //client.geCustomerDetails("2016-04-01_11");
    }

}
