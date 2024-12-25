package COM.CYDEO.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTests {


   @BeforeAll
    public static void init(){
       RestAssured.baseURI = "http://3.90.35.207:8000";

    }

    /*
    Given content type is application/json
    when user sends Get request /api/spartans endpoint
    Then status code should be 200
    And Content type should be application/json
     */

    @Test
    public void getAllSpartan(){
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        assertEquals(200,response.getStatusCode());

        response.prettyPrint();
    }

    /*
    Given Accept type application/xml
    When user send Get request to /api/spartans/10 endpoint
    Then status code must be 406
    And response Content type must be application/xml;charset=UFT-8;
     */
    @DisplayName("Accept, application/xml 406")
    @Test
    public void xmlTest(){
       // Given Accept type application/xml
        //    When user send Get request to /api/spartans/10 endpoint

        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get("/api/spartans/10");
        //    Then status code must be 406
        assertEquals(406,response.statusCode());

        //And response Content type must be application/xml;charset=UFT-8;
        assertEquals("application/xml;charset=UTF-8",response.contentType());




    }

}
