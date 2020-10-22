package techproedbatch5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

public class GetRequest13 extends TestBase {
    @Test
    public void get01() {
        Response response = given().
                spec(spec02).
                when().
                get();
        // response.prettyPrint();

        // ilk 5 ismin "Tiger Nixon","Garrett Winters","Ashton Cox","Cedric Kelly","Airi Satou" oldugunu verify ediniz

        //1.yol istenilmeyen tavsiye edilmez

        JsonPath jsonPath = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("data[0].employee_name"), "Tiger Nixon",
                "incorrect name");
        softAssert.assertEquals(jsonPath.getString("data[1].employee_name"), "Garrett Winters",
                "incorrect name");
        softAssert.assertEquals(jsonPath.getString("data[2].employee_name"), "Ashton Cox",
                "incorrect name");
        softAssert.assertEquals(jsonPath.getString("data[3].employee_name"), "Cedric Kelly",
                "incorrect name");
        softAssert.assertEquals(jsonPath.getString("data[4].employee_name"), "Airi Satou",
                "incorrect name");


        //2.yol
        List<String> nameList = new ArrayList<>();

        nameList.add("Tiger Nixon");
        nameList.add("Garrett Winters");
        nameList.add("Ashton Cox");
        nameList.add("Cedric Kelly");
        nameList.add("Airi Satou");

        for (int i = 0; i < nameList.size(); i++) {
            softAssert.assertEquals(jsonPath.getString("data[" + i + "].employee_name"), nameList.get(i),
                    "incorrect name");
        }

        //3.yol

        List<Map> actualList = jsonPath.getList("data");//butun data yi alinmis oldu
        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put(0, "Tiger Nixon");
        expectedMap.put(1, "Garrett Winters");
        expectedMap.put(2, "Ashton Cox");
        expectedMap.put(3, "Cedric Kelly");
        expectedMap.put(4, "Airi Satou");

        for (int i = 0; i < expectedMap.size(); i++) {
            softAssert.assertEquals(actualList.get(i).get("employee_name"), expectedMap.get(i), "incorrect value");
        }

        softAssert.assertAll();


    }
}
