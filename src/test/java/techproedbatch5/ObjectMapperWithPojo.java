package techproedbatch5;

import static io.restassured.RestAssured.*;

import Utilities.JsonUtil;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectMapperWithPojo extends TestBase {

    // Java objesini Json formatina Pojo ile cevirme.
    @Test
    public void javaToJson() {

        BookingDates bookingDates = new BookingDates("2020-10-20", "2020-10-25");

        System.out.println(bookingDates);


        String jsonFromPojo = JsonUtil.convertJavaToJson(bookingDates);
        System.out.println(jsonFromPojo);

    }

    @Test
    public void jsonToJava() {
        Response response = given().spec(spec01).get("booking/3");
        //response.prettyPrint();
        // Json data yi pojo class i ile java formatina cevirecegiz.

        Booking jsonToPojoApi = JsonUtil.convertJsonToJava(response.asString(), Booking.class);

        System.out.println(jsonToPojoApi);

        BookingDates bookingDates = new BookingDates("2015-07-10", "2016-05-21");
        Booking booking = new Booking("Jim", "Brown", 235, true, bookingDates, null);

        response.
                then().
                assertThat().
                statusCode(200);

        assertEquals(booking.getBookingdates().getCheckin(), jsonToPojoApi.getBookingdates().getCheckin());
        assertEquals(booking.getBookingdates().getCheckout(), jsonToPojoApi.getBookingdates().getCheckout());
        assertEquals(booking.getFirstname(), jsonToPojoApi.getFirstname());
        assertEquals(booking.getLastname(), jsonToPojoApi.getLastname());
        assertEquals(booking.getTotalprice(), jsonToPojoApi.getTotalprice());
        assertEquals(booking.getDepositpaid(), jsonToPojoApi.getDepositpaid());
        assertEquals(booking.getAdditionalneeds(), jsonToPojoApi.getAdditionalneeds());


    }
}
