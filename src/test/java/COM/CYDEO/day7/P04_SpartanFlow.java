package COM.CYDEO.day7;

import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Data
public class P04_SpartanFlow extends SpartanTestBase {

private static int id = 133;
    /*

    Create a Spartans to wun below testcase dynamically

    -Post /api/spartans
    Create a spartan Map
    name = "API Flow POST"
    gender = "Male"
    phone = 12312312311

    - verify status code 201
    - message is "A Spartan is Born!"
    - take spartanId from response and save as a global variable

    - GET Spartan with spartanId /api/spartans/{id}

    - verify status code 200
    - verify name is API Flow POST

    - Put Spartan with spartanId /api/spartans/{id}
         Create a spartan Map
         name = "API PUT Flow"
         gender = "Female"
         phone=32132132131

         - verify code 204
    -Get Spartan with spartanId /api/spartans/{id}
    - verify status code 200
    - Verify name is API Put Flow

    -Delete Spartan with spartanId /api/spartan/{id}

    - verify status code 204

    -Get Spartan with spartanId /api/spartans/{id}

    - verify status code 404


    Challenges
    create @Test annotated method for each request
    put them in order to execute as expected
    -Use your gooling skills
    - How to run Junit5 in order?
     */

    //  Create a Spartans to wun below testcase dynamically
    @DisplayName("Create one spartan using POST")
    @Test
    public void post(){
        Map<String,Object> spartan = new LinkedHashMap<>();
        spartan.put("name", "API Flow POST");
        spartan.put("gender", "Male");
        spartan.put("phone", "12312312311");

        System.out.println("spartan = " + spartan);

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("message", is("A Spartan is Born!"))
                .extract().jsonPath();


    }
@Test
    @DisplayName("Get spartan using id ")
    public void getSpartan(){
    Map<String,Object> spartan = new LinkedHashMap<>();
    spartan.put("name", "API Flow POST");
    spartan.put("gender", "Male");
    spartan.put("phone", "12312312311");

    JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", id)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertEquals("API Flow POST",jsonPath.getString("name"));

    }

    /*
     - Put Spartan with spartanId /api/spartans/{id}
         Create a spartan Map
         name = "API PUT Flow"
         gender = "Female"
         phone=32132132131

         - verify code 204

     */
    @Test
    public void putSpartan(){
        Map<String,Object> spartan = new LinkedHashMap<>();
        spartan.put("name", "API PUT Flow ");
        spartan.put("gender", "Female");
        spartan.put("phone", "32132132131");

        given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .when()
                .body(spartan)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }



}
