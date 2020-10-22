package techproedbatch5;


import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;


public class GetRequest01 {
    // Rest-Assured kullanarak API testing.

    @Test
    public void getMethod01() {

        given(). // restAssured den gelen bir method.
                when(). // when den sonra  --get, post,put,patch , delete-- methodlari calistirilir.
                get("https://restful-booker.herokuapp.com/booking"). // get te sadece EndPoint kullanilir.(yani URL)
                then().//dogrulama gerek
                assertThat().
                statusCode(200);
    }

    @Test
    public void getMethod02() {
        Response response = given().
                when().
                get("https://restful-booker.herokuapp.com/booking");
        // response body i console a yazdirmak icin kullanilir.
        // response.prettyPrint();
        // status code u console ekraninda gormek icin
        System.out.println("Status Code: " + response.getStatusCode());

        System.out.println("Status Line: " + response.getStatusLine());
        // Headers line information
        System.out.println("Content Type: " + response.getContentType());

        System.out.println("Content Type: " + response.header("Content-Type"));

        //System.out.println(response.getHeaders());

        System.out.println(response.header("Date"));

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

    }
}