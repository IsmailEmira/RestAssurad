package COM.CYDEO.Day4;

import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithJsonPath extends SpartanTestBase {


    /*
    Given accept type is Json
    And path param id is 10
    When user send a get request to "api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json"
    And response payload values match the following:
    id is 10
    name is "Lorenza
    gender is "Female"
    phone is 3312820936
     */

    @DisplayName("Get spartan with Response path")
    @Test
    public void Test1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when()
                .get("api/spartans/{id}");
//        Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
//        And content-type is "application/json"
        assertEquals("application/json", response.contentType());

//        And response payload values match the following:
//        id is 10
//        name is "Lorenza
//        gender is "Female"
//        phone is 3312820936
        // we saved our response as JsonPath object
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");
        // Assertion
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936L, phone);


    }
}
