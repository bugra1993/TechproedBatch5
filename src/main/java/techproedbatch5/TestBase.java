package techproedbatch5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.json.JSONObject;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class TestBase {

    protected RequestSpecification spec01;
    protected RequestSpecification spec02;
    protected RequestSpecification spec03;

    @Before
    public void setup01() {
        spec01 = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
    }

    @Before
    public void setup02() {
        spec02 = new RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
                build();
    }

    @Before
    public void setup03() {
        spec03 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();
    }

    protected Response createRequestBodyByJsonObjectClass() {

        JSONObject jsonBookingDatesBody = new JSONObject();


        jsonBookingDatesBody.put("checkin", "2020-05-202");
        jsonBookingDatesBody.put("checkout", "2020-05-05");

        JSONObject jsonRequestBody = new JSONObject();

        jsonRequestBody.put("firstname", "Hasan");
        jsonRequestBody.put("lastname", "Kara");
        jsonRequestBody.put("totalprice", 123);
        jsonRequestBody.put("depositpaid", true);
        jsonRequestBody.put("bookingdates", jsonBookingDatesBody);
        jsonRequestBody.put("additionalneeds", "Wifi");

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(jsonRequestBody).
                when().
                post("/booking");


        return response;

    }

    protected Response createRequestBodyMap() {
        Map bookingDatesMap = new HashMap<>();

        bookingDatesMap.put("checkin", "2020-05-02");
        bookingDatesMap.put("checkout", "2020-05-05");

        Map<String, Object> requestBodyMap = new HashMap<>();

        requestBodyMap.put("firstname", "Hasan");
        requestBodyMap.put("lastname", "Kara");
        requestBodyMap.put("totalprice", 123);
        requestBodyMap.put("depositpaid", true);
        requestBodyMap.put("bookingdates", bookingDatesMap);
        requestBodyMap.put("additionalneeds", "Wifi");

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(requestBodyMap).
                when().
                post("/booking");

        response.prettyPrint();


        response.
                then().
                assertThat().
                statusCode(200);

        return response;
    }
}
