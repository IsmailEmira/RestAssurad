package COM.CYDEO.Day3;

import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithParameters extends SpartanTestBase {

    /*
    Given accept type is Json
    when Id parameter value is 24
    Then response status code should be 200
    And response content-type: application/json
    And "Julio" should be in response payload(body)
     */

    @DisplayName(" Get spartan with content-type: application/json /{id} path param with 24")
    @Test
    public void getOneSpartanWithId() {
        // Given accept type is Json
        // When Id parameter value is 24
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 24)
                .when()
                .get("/api/spartans/{id}");

        // Verify the status code 200

        assertEquals(200, response.statusCode());

        //And response content-type: application/json

        assertEquals("application/json", response.contentType());

        //And "Julio" should be in response payload(body)

        assertTrue(response.body().asString().contains("Julio"));


    }

     /*
    Given accept type is Json
    And Id parameter value is 500
    When user sends Get request to /api/spartans/{id}
    Then response status code should be 404
    And response content-type: application/json
    And "Not Found" message should be in response payload(body)
     */

    @DisplayName("Get Spartan response when /api/spartans/{id} / path param 500 ")
    @Test
    public void Test2() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");

        // verify status code be 404
        assertEquals(404,response.statusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());

        // verify the content-type
        assertEquals("application/json",response.contentType());

        // verify the body message "Not Found"

        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
    Given Accept type is Json
    And query parameter values are:
       gender|female
       nameContains|e
     When user sends Get request to /api/spartans/search
     Then response status could should be 200
     And response content-type : application/json
     And "Female" should be in response payload
     And "Janette" should be in response payload
     */

    @DisplayName("SEARCH FOR SPARTAN /api/spartans/search - with query parameters ")
    @Test
    public void Test3() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("gender","female")
                .queryParam("nameContains","e")
                .when()
                .get("/api/spartans/search");
        // verify the status code is 200
        assertEquals(200,response.statusCode());
        // verify the content-type is application/json
        assertEquals("application/json",response.contentType());
        // verify the body
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();



    }

    @DisplayName("SEARCH FOR SPARTAN /api/spartans/search - with query parameters ")
    @Test
    public void Test4() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get("/api/spartans/search");
        // verify the status code is 200
        assertEquals(200, response.statusCode());
        // verify the content-type is application/json
        assertEquals("application/json", response.contentType());
        // verify the body
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

        response.prettyPrint();


    }


    }
