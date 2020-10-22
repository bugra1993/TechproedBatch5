package techproedbatch5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class GetRequest12 extends TestBase {

    @Test
    public void get01() {
        Response response = given().
                spec(spec03).
                when().
                get();
        //response.prettyPrint();

        List<Map<Object, Object>> listOfMap = response.as(ArrayList.class);

        //System.out.println(listOfMap);

        System.out.println(listOfMap.get(0));

        //200 tane id oldugunu verify edin

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(listOfMap.size(), 200);

        softAssert.assertEquals(listOfMap.get(120).get("completed"), true, "deger yanlis");
        // sondan bir onceki elemanin title degerinin dogrulugunu kontrol ediniz.
        softAssert.assertEquals(listOfMap.get(listOfMap.size() - 2).get("title"), "numquam repellendus a magnam",
                "wrong title description");

        softAssert.assertAll();
    }
}
