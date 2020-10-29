package techproedbatch5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/*
    POJO : Plain Old Java Object
    * Json formatindaki data yi Class lara cevirme islemine denir.

    http://www.jsonschema2pojo.org adresine gidilir.
    Data paratezden diger paranteze kopyalanip, siteye tasinir.
    Sag tarafta class name --> Booking  target--> java source type-->Json
    daha sonra preview ile class lar olusturulur.
    Key lerin hepsi variable olarak tanimlandi.

 */


public class PostRequest04 extends TestBase {

    @Test
    public void post01() {
        BookingDates bookingDates = new BookingDates("2020-05-02", "2020-05-05");
        Booking booking = new Booking("Hasan", "Kara", 123, true, bookingDates, "Wifi");

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(booking).
                when().
                post("/booking");
        response.prettyPrint();


        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("booking.firstname"), booking.getFirstname());
        softAssert.assertEquals(jsonPath.getString("booking.lastname"), booking.getLastname());
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"), booking.getTotalprice());
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"), booking.getDepositpaid());
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"), booking.getBookingdates().getCheckin());
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"), booking.getBookingdates().getCheckout());
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"), booking.getAdditionalneeds());

        softAssert.assertAll();

    }


}
