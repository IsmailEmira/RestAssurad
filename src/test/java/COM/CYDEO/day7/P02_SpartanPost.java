package COM.CYDEO.day7;

import COM.CYDEO.Day6.Pojo.Spartan;
import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_SpartanPost extends SpartanTestBase {

    /*
    Given accept type is Json
    And Content type is Json
    And request json body is:
    {
    "gender":"Male",
    "name":"John Doe",
    "phone":8877445596
    }
    When user sends Post request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    and json payload/response/body should contain:
    verify the success value is 'A Spartan is Born!'
    "name": "John Doe"
    "gender": "Male"
    "phone": 8877445596
     */
    @DisplayName("Post Spartan with String body")
    @Test

    public void test1() {
        String requestBody = " {\n" +
                "    \"gender\":\"Male\",\n" +
                "    \"name\":\"John Doe\",\n" +
                "    \"phone\":8877445596\n" +
                "    }";
        JsonPath jsonPath = given().accept(ContentType.JSON) // please send me Json response body
                .and()
                .contentType(ContentType.JSON) // I am sending you Json request Body
                .body(requestBody)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("message", is("A Spartan is Born!"))
                .extract().jsonPath();

        // request body verification
        assertEquals("John Doe", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(8877445596l, jsonPath.getLong("data.phone"));

        // I want to get id out of the response body, to delete or send get request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);
    }

    @DisplayName("Post Spartan with String body using Map")
    @Test
    public void test2() {
        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name", "John Doe");
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("phone", "8877445596");
        JsonPath jsonPath = given().accept(ContentType.JSON) // please send me Json response body
                .and()
                .contentType(ContentType.JSON) // I am sending you Json request Body
                .body(requestBodyMap)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("message", is("A Spartan is Born!"))
                .extract().jsonPath();

        // request body verification
        assertEquals("John Doe", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(8877445596l, jsonPath.getLong("data.phone"));

        // I want to get id out of the response body, to delete or send get request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);
    }

    @DisplayName("Post Spartan with String body using Pojo")
    @Test
    public void test3() {
        Spartan spartan = new Spartan();
        spartan.setName("Harlod Finch");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);

        System.out.println("spartan = " + spartan);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON) // please send me Json response body
                .and()
                .contentType(ContentType.JSON) // I am sending you Json request Body
                .body(spartan)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("message", is("A Spartan is Born!"))
                .extract().jsonPath();



    }

    @DisplayName("Post Spartan with String body using Pojo")
    @Test
    public void test4() {
        Spartan spartan = new Spartan();
        spartan.setName("Harlod Finch");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);

        System.out.println("spartan = " + spartan);

        JsonPath jsonPath = given().log().body()
                .accept(ContentType.JSON) // please send me Json response body
                .and()
                .contentType(ContentType.JSON) // I am sending you Json request Body
                .body(spartan)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("message", is("A Spartan is Born!"))
                .extract().jsonPath();

        // request body verification
        assertEquals("Harlod Finch", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(1234567890l, jsonPath.getLong("data.phone"));

        // I want to get id out of the response body, to delete or send get request later on
        int id = jsonPath.getInt("data.id");
        System.out.println("id = " + id);

        //Send get request to the Spartan that is created then deserialize and compare?

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();
         // get json response and desrialize to spartan class
        Spartan spartanGet = response.as(Spartan.class);

        System.out.println("spartanGet = " + spartanGet);

        // verify names that we sent and get is mayching
        assertEquals(spartan.getName(),spartanGet.getName());

    }}
