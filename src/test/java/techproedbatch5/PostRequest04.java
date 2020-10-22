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

/*
    POJO : Plain Old Java Object
    * Json formatindaki data yi Class lara cevirme islemine denir.

    http://www.jsonschema2pojo.org adresine gidilir.
    Data paratezden diger paranteze kopyalanip, siteye tasinir.
    Sag tarafta class name --> Booking  target--> java source type-->Json
    daha sonra preview ile class lar olusturulur.
    Key lerin hepsi variable olarak tanimlandi.

 */


public class PostRequest04 extends TestBase {


}
