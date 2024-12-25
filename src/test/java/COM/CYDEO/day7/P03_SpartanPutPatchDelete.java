package COM.CYDEO.day7;

import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P03_SpartanPutPatchDelete extends SpartanTestBase {

    @DisplayName("Put Spartan with Map")
    @Test
    public void test1(){
        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "John Doe");
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("phone", "8877445596");
       // put will update existing record so we choose one the existing ID, make sure it exist in your in ypur
       int id = 115;

       given().contentType(ContentType.JSON)
               .pathParam("id",id)
               .when()
               .body(requestBodyMap)
               .when().put("/api/spartans/{id}")
               .then().statusCode(204);

    }

    @DisplayName("Patch Spartan with Map")
    @Test
    public void test2(){
        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "John Doe");
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("phone", "8877445596");
        requestBodyMap.put("name","John Doe PATCH");
        // put will update existing record so we choose one the existing ID, make sure it exist in your in ypur
        int id = 115;

        given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .body(requestBodyMap)
                .when().patch("/api/spartans/{id}")
                .then().statusCode(204);

    }

    @DisplayName("Delete spartan")
    @Test
    public void test3(){
        int id = 116;
        given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //after deleted same spartan should get code 404
        given().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);

    }



}
