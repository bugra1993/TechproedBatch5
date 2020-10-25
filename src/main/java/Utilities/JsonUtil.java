package Utilities;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    //bu class icerisine serialization ve deserialization methodlari olusturacagiz ve ihtiyac oldukca kullanacagiz


    private static ObjectMapper mapper; // diger class lardan  ulasabilmek icin methodlarin disina class icinde olusturduk

    //method lardan once olusmasi icin static block icxerisinde mapper  objesi olusturduk

    static {
        mapper = new ObjectMapper();
    }

    // java object ini JSon format ina ceviren method.

    public static String covertJavaToJson(Object object) {// veri tipi olarak ne yazarsak onu Json formatina cevirir
        String jsonResult = "";
        try {
            jsonResult = mapper.writeValueAsString(object);//Json objesi Json formatina cevrildi. Olusabilecek hatalar icin
            //try catch islemi yapildi.
        } catch (JsonGenerationException e) {
            System.out.println("Got an error while converting Java format to Json" + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("Got an error while converting Java format to Json" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Got an error while converting Java format to Json." + e.getMessage());
        }
        return jsonResult;
    }
    // Json fromatini Java formatina cevirme ==> DeSerialization


    //public static Object covertJsonToJava() {   }

    public static <T> T covertJsonToJava(String json, Class<T> cls) {
        // Generic bir method urettik. Bu method ile Json formatini cevirmek istedigimiz format a gore cevirecegiz.
        // return type method kullanilirken belirtiliyor.


        T javaResult = null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (JsonParseException e) {
            System.out.println("Got an error while converting Json object to Java." + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("Got an error while converting Json object to Java." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Got an error while converting Json object to Java." + e.getMessage());
        }

        return javaResult;

        //Tester lar bu methodu cok kullanir. Asil kullanilacak olan methoddur.
        // Bu islem DeSerialization.

    }

}
