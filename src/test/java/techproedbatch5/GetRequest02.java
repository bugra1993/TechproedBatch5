package techproedbatch5;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class GetRequest02 {

    @Test
    public void get01() {
        given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking").
                then().
                assertThat().
                statusCode(200).
                contentType("application/json");

    }

    // negative scenario
    @Test
    public void get02() {
        Response response = given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking/1001");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(404).
                contentType("text/plain; charset=utf-8");
        // negative scenario does not include content type

    }

    @Test
    public void get03() {
        Response response = given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking/1001");
        response.prettyPrint();

        assertEquals(404, response.getStatusCode());
        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("Techproed"));


    }

}
