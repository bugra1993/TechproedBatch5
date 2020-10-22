package techproedbatch5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest05 {

    @Test
    public void get01() {
        Response response = given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking/5");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Sally"),
                        "totalprice", Matchers.equalTo(583),
                        "bookingdates.checkin", Matchers.equalTo("2016-03-11"));

    }
}
