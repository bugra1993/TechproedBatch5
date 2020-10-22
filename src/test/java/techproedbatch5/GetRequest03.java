package techproedbatch5;


import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class GetRequest03 {

    @Test
    public void get01() {
        Response response = given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking/7");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                body("firstname", Matchers.equalTo("Susan")).
                body("lastname", Matchers.equalTo("Brown")).
                body("totalprice", Matchers.equalTo(387)).
                body("depositpaid", Matchers.equalTo(false)).
                body("bookingdates.checkin", Matchers.equalTo("2018-04-26")).
                body("bookingdates.checkout", Matchers.equalTo("2018-07-02"));

    }
}
