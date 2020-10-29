package techproedbatch5;


import io.restassured.path.json.JsonPath;

import org.junit.Test;
import io.restassured.response.Response;

import org.testng.asserts.SoftAssert;


public class PostRequest03b extends TestBase {


    @Test
    public void post01() {
        Response response = createRequestBodyMap();
        JsonPath jsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("booking.firstname"), requestBodyMap.get("firstname"));
        softAssert.assertEquals(jsonPath.getString("booking.lastname"), requestBodyMap.get("lastname"));
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"), requestBodyMap.get("totalprice"));
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"), requestBodyMap.get("depositpaid"));
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"), bookingDatesMap.get("checkin"));
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"), bookingDatesMap.get("checkout"));
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"), requestBodyMap.get("additionalneeds"));


        softAssert.assertAll();

    }

}
