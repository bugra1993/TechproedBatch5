package techproedbatch5;

import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteRequest01 extends TestBase {

    //sadece end point gerekli.


    @Test
    public void delete01() {
        Response responseGet = given().
                spec(spec03).
                when().
                get("/3");
        responseGet.prettyPrint();

        Response responseDel = given().
                spec(spec03).
                when().
                delete("/3");
        responseDel.prettyPrint();


        // responseDel yazdirildiginda not found cevabi gelirse status code  404 ile test edilir.
        // Eger bos bir satir donerse status code 200 ile test edilir.

        responseDel.
                then().
                statusCode(200);


        // hard assert

        assertTrue(responseDel.getBody().asString().contains(" "));

        // soft assertion

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(responseDel.getBody().asString().contains(" "));
        softAssert.assertAll();
    }

}
