/**
 * Created by sree on 21/05/16.
 */


import com.google.gson.Gson;
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


/**
 * Created by sree on 19/05/16.
 */
public class ElasticSearchClient {
    //TODO write a client to send json data

    //TODO logic to generate special offers for customers based on phone number



    /**
     * Given all the data is in a index without reponse
     * Insert a unique field describing the bill (The date along with the bill number for the day)
     * Add a field for total amount
     * all the data needs to saved in a different index and must be fetched from that index and saved in a new one
     * the newly saved must have the rating and based on which all the other operations must be performed
     *
     */
    public void restClient() {
        given().content("{ \"message\" : \"hello world\"}").with().contentType(JSON).and().expect().body(equalTo("hello world")).when().post("/jsonBody");
    }

    public String geCustomerDetails(String uniqueID) throws UnknownHostException {

        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));


        BoolQueryBuilder query = boolQuery()
                .must(termQuery("billNumber","1"))
                .must(termQuery("date","2016-04-01"));

        SearchResponse response = client.prepareSearch("mylapore")
                .setTypes("Invoice")
                .setSearchType(SearchType.DFS_QUERY_AND_FETCH)
                .setQuery(query)
                .execute()
                .actionGet();

         // on shutdown

        client.close();
        return response.toString();
    }

    public void putCustomerDetails(Data data) throws UnknownHostException {

        Gson gson = new Gson();
        gson.toJson(data);

        given().baseUri("http://localhsot:9200").body(gson).with().contentType("application/json").post("/mylapore/Invoice/"+data.getDate()+"-"+data.getBillNumber());

    }

    public static void main(String[] args) throws UnknownHostException {
        ElasticSearchClient client = new ElasticSearchClient();
        client.geCustomerDetails("j");
    }
}
