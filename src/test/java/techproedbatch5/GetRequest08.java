package techproedbatch5;

import io.restassured.path.json.JsonPath;
import org.junit.Test;
import io.restassured.response.Response;
import org.hamcrest.Matchers;


import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class GetRequest08 extends TestBase {

    /*
     When I send a GET request to REST API URL
          https://restful-booker.herokuapp.com/booking/5
     Then HTTP Status Code 200 olsun
     And response content type “application/JSON” olsun
     And response body asagidaki gibi olsun;
     {
     "firstname": "Sally",
     "lastname": "Ericsson",
     "totalprice": 111,
     "depositpaid": false,
     "bookingdates": {
                       "checkin": "2017-05-23",
                       "checkout":"2019-07-02" }
     }
    */

    @Test
    public void get01() {
        spec01.pathParam("bookingid", 5);

        Response response = given().
                spec(spec01).
                when().
                get("/booking/{bookingid}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("firstname"));
        System.out.println(jsonPath.getString("lastname"));
        System.out.println(jsonPath.getString("totalprice"));
        System.out.println(jsonPath.getString("depositpaid"));

        assertEquals("firstname istenilen gibi degil", "Jim", jsonPath.getString("firstname"));
        assertEquals("lastname istenilen gibi degil", "Brown", jsonPath.getString("lastname"));



    }
}
