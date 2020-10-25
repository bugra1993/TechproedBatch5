package techproedbatch5;

import Utilities.JsonUtil;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class ObjectMapperWithMap extends TestBase {

    @Test
    public void JavaToJson() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(101, "Ali");
        map.put(102, "Veli");
        map.put(103, "Can");

        System.out.println(map);

        //map objesini Json formatina cevirelim ==> Serialization

        String jsonFormMap = JsonUtil.covertJavaToJson(map);
        System.out.println(jsonFormMap);


    }

    // API dan get methodu ile gelen formatindaki datayi map e cevirecegiz. Dha sonra ve verification yapacagiz.

    @Test
    public void JsonToJava() {
        Response response = given().
                spec(spec01).
                when().
                get("booking/3");

        response.prettyPrint();

        //Object Mapper class ile bu json data yi map e Java map formatina cevirelim.

        Map<String, Object> jsonToMapApi = JsonUtil.covertJsonToJava(response.asString(), Map.class);

        System.out.println(jsonToMapApi);

        /*
        1) API dan gelen Json formatini map e cevirdik.
        2) Test case den gelen data lari map ile obje yapacagiz.
        3) Olusan bu iki map objeyi verification yapacagiz.
         */

        // 2. Adim
        Map<String, Object> jsonToMapTestCase = new HashMap<>();
        jsonToMapTestCase.put("firstname", "Mary");
        jsonToMapTestCase.put("lastname", "Smith");
        jsonToMapTestCase.put("totalprice", 887);
        jsonToMapTestCase.put("depositpaid", true);
        jsonToMapTestCase.put("additionalneeds", "Breakfast");

        response.
                then().
                assertThat().
                statusCode(200);
        assertEquals(jsonToMapTestCase.get("firstname"), jsonToMapApi.get("firstname"));
        assertEquals(jsonToMapTestCase.get("lastname"), jsonToMapApi.get("lastname"));
        assertEquals(jsonToMapTestCase.get("totalprice"), jsonToMapApi.get("totalprice"));
        assertEquals(jsonToMapTestCase.get("depositpaid"), jsonToMapApi.get("depositpaid"));
        assertEquals(jsonToMapTestCase.get("additionalneeds"), jsonToMapApi.get("additionalneeds"));


        // Mapler tek boyutlu (nested json olmayan) Json lar icin kolay bir yontemdir.


    }


}
