package techproedbatch5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.asserts.SoftAssert;


import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

public class PostRequest01 extends TestBase {

    /*

    Get request olustumak icin sadece endpoint gerekiyordu.
    Post reuqest olusturmak icin gerekenler;

   * 1) endpoint sart
   * 2) request body yani kaydedilecek olan data
    3) autorization sar(eger verilmemisse)
    4) Accept type istege bagli
    5) Content type yani icerik tipi

    Interview sorusu : * get ile post request arasindaki farklar nedir?
    - get icin endpoint gereklidir. autorazation her test icin zorunlu olmayabilir.
    * post request icin endpoint ve body(data) gereklidir.
    Get var olan data lari gormek icin kullanilir.
    Post ise olmayan datayi olusturmak icin kullanilir. (yeni data olusturulur)

    NOTE: SQL de doldurulmasi zorunlu alanlar vardir. not null, primary key, foreign key vb.(constraint)
          API developer olusturulacak data nin hangi bolumlerine zorunlu klidi ise o kisimlari bos gecilmez.
          Post Request olusturulurken bu kisimlar kesinlikle doldurulmali.
          Sweger dokumantasyonda bu kisimlar acikca yazar.
          Eger bos gecilirse '400' Bad Request hatasi aliriz.

                *  POST Scenario:
            Content type Json olsun  (Content Type demektir.)
             When
             POST request yolladigimda
             1) https://restful-booker.herokuapp.com/booking
             2) Request Body
             {
             "firstname": "Hasan",
             "lastname": "Kara",
             "totalprice": 123,
             "depositpaid": true,
             "bookingdates": {
             "checkin": "2020-05-02",
             "checkout": "2020-05-05"
             },
             "additionalneeds": "Wifi"
             }
             Then
             Status Code 200 olmali
             Response Body, Request Body ile ayni olduğunu verfy ediniz




    */


    @Test
    public void post01() {

        String jsonReqBody = "      {\n" +
                "             \"firstname\": \"Hasan\",\n" +
                "             \"lastname\": \"Kara\",\n" +
                "             \"totalprice\": 123,\n" +
                "             \"depositpaid\": true,\n" +
                "             \"bookingdates\": {\n" +
                "             \"checkin\": \"2020-05-02\",\n" +
                "             \"checkout\": \"2020-05-05\"\n" +
                "             },\n" +
                "             \"additionalneeds\": \"Wifi\"\n" +
                "             }";


        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(jsonReqBody).
                when().
                post("/booking");

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("booking.firstname"), "Hasan");
        softAssert.assertEquals(jsonPath.getString("booking.lastname"), "Kara");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"), 123);
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"), true);
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"), "2020-05-02");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"), "2020-05-05");
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"), "Wifi");


        softAssert.assertAll();


    }


}
