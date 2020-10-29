package techproedbatch5;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

public class GetRequest11 extends TestBase {

    /*
    GSON 1) Json formatindaki datalari Java objectlerine donusturur.(De serialazation)
         2) Java objectlerinin Json formatina donusturur.(Serialazation)
         json --> GSON ---> Java (object ler map lere ya da list lere cevrilis) // De Serialazation
         Java --> GSON ---> Json // Seriazalation
     */

    @Test
    public void get01() {
        Response response = given().
                spec(spec03).
                when().
                get("/2");

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        HashMap<String, String> map = response.as(HashMap.class); // De Seriazalation
        System.out.println(map);

        System.out.println(map.keySet()); // id,completed,title,userId

        System.out.println(map.values());

        // completed key degerini false oldugunu verify ediniz

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(map.get("completed"), false, "beklenen deger gelmedi");

        //userId,title ve id degerlerini verify edelim.

        softAssert.assertEquals(map.get("userId"), 1, "userId 1 degil");
        softAssert.assertEquals(map.get("title"), "quis ut nam facilis et officia qui",
                "beklenen deger gelmedi");
        softAssert.assertEquals(map.get("id"), 2, "Id 2 degil");

        softAssert.assertAll();

        // Map objesini json formatina cevirme

        Gson gson = new Gson();
        System.out.println(gson.toJson(map));


    }
}
