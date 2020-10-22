package techproedbatch5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

public class GetRequest10 extends TestBase {

    @Test
    public void get01() {
        Response response = given().
                spec(spec02).
                when().
                get();
        //response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        List<Integer> idList = jsonPath.getList("data.findAll{(it.id)>10}.id");
        //butun herseyi al buradaki id leri 10 dan buyukse liste ekle.
        System.out.println(idList);
        softAssert.assertEquals(idList.size(), 14, "element count is not 14");

        List<Integer> ageList = jsonPath.getList("data.findAll{(it.employee_age)<30}.employee_age");

        System.out.println(ageList);

        Collections.sort(ageList);
        System.out.println(ageList);

        softAssert.assertTrue(ageList.get(ageList.size() - 1).equals(23), "yas istenen deger degildir");

        List<Integer> salaryList = jsonPath.getList("data.findAll{(it.employee_salary)>350000}.employee_name");
        System.out.println(salaryList);

        softAssert.assertTrue(salaryList.contains("Charde Marshall"));

        softAssert.assertAll();

    }
}
