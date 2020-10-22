package techproedbatch5;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class GetRequest07 extends TestBase {

    @Test
    public void get01() {
        Response response = given().
                spec(spec01).
                when().
                get("/booking?firstname=Susan");

        response.prettyPrint();

        assertTrue(response.getBody().asString().contains("bookingid"));

    }

    @Test
    public void get02() {
        spec01.pathParam("firstname", "Susan");
        spec01.queryParam("lastname", "Ericsson");
    }
}
