package techproedbatch5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class GetRequest09 extends TestBase {

    @Test
    public void get01() {
        Response response = given().
                spec(spec02).
                when().
                get();
        response.prettyPrint();
        //json path objesi olusturuldu.

        JsonPath jsonPath = response.jsonPath();

        //tum employee islemlerini consol a yazdiralim.

        //System.out.println(jsonPath.getString("data.employee_name"));

        //2. iscinin ismini 'Garrett Winters' oldugunu 'verify'(Soft assertion) ediniz.

        //assertEquals("isim istendigi gibi degil",
        //       "Garrett Winters",jsonPath.getString("data[1].employee_name"));


        /*
        Soft assertion icin 3 adimi takip etmek gerekir.
        1)SoftAssert class indan bir obje uretilir.
        2)Objeyi kullanarak assertion  yapilir
        3)assertAll ile test islemi tamamlanir.

         */
        SoftAssert softAssert = new SoftAssert();


        softAssert.assertEquals(jsonPath.getString("data[1].employee_name"), "Garrett Winters");


        //Assertion hard test
        //verification 'verify' soft test

        //'Herrod Chandler' olmali
        softAssert.assertTrue(jsonPath.getList("data.employee_name").contains("Herrod Chandler"),
                "isim istenildigi gibi degil");
        // isci sayisinin 24 oldugun verify ediniz.
        softAssert.assertEquals(jsonPath.getList("data").size(), 24,
                "size is not equals 24");


        softAssert.assertEquals(jsonPath.getInt("data[6].employee_salary"), 137500,
                "salary is not true");


        softAssert.assertAll();


    }
}
