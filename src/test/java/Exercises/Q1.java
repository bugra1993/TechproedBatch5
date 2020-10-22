package Exercises;


/*

     When Asagidaki Endpoint'e bir GET request yollayalim
     https://restful-booker.herokuapp.com/booking/2
     And Accept type “application/json” mi?
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Mark"
     And lastname "Brawn"
     And checkin date "2017-12-30"
     And checkout date "2019-01-12"
*/


import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class Q1 {
    @Test
    public void get01() {
        Response response = given().
                accept("application/json").
                when().
                get(" https://restful-booker.herokuapp.com/booking/2");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                body("firstname", Matchers.equalTo("Mark"),
                        "lastname", Matchers.equalTo("Brown"),
                        "bookingdates.checkin", Matchers.equalTo("2018-01-09"),
                        "bookingdates.checkin", Matchers.equalTo("2020-05-03"));


    }



}
