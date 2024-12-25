package COM.CYDEO.Day5;

import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P04_DeserializationToCollections extends SpartanTestBase {

    /*
    Given accept type is application/json
    And path param id = 10
    When send Get request to /api/spartans
    Then status code is 200
    And content type is json
    And spartan data matching
         id > 10
         name>Lorenza
         gender >Female
         phone>3312820936
     */

    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        // solution 1
        Map<String, Object> spartanMap = response.as(Map.class);

        System.out.println("spartanMap = " + spartanMap);

        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");

        System.out.println("id = " + id);
        System.out.println("name = " + name);

        //solution 2
        JsonPath jsonPath = response.jsonPath();

        Map<String, Object> jsonPathMap = jsonPath.getMap("");
        System.out.println("jsonPathMap = " + jsonPathMap);

    }

    @DisplayName("Get All Spartan with Java Collections")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        List<Map<String,Object>> map = response.as(List.class);
        System.out.println("map = " + map);


    }
}
