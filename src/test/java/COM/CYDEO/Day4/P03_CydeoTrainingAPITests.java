package COM.CYDEO.Day4;

import COM.CYDEO.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {

    /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    and Date header is exist
    And Server header is envoy
    And verify following
                 firstname mark
                 batch 13
                 major math
                 emailAddress mark@email.com
                 companyName Cydeo
                 street 777 5th Ave
                 zipcode 33222
     */

    @DisplayName("Get student id with path param 2 ")
    @Test
    public void Test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        // verify the status code
        assertEquals(200, response.statusCode());
        // verify the content type
        assertEquals("application/json;charset=UTF-8", response.contentType());

        //And Date header is exist
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //And server header is envoy
        assertEquals("envoy", response.header("server"));
         // create jsonpath object
        JsonPath jsonPath = response.jsonPath();
        // firstname mark
        assertEquals("Mark",jsonPath.getString("students[0].firstName"));
        // batch 13
        assertEquals(13,jsonPath.getInt("students[0].batch"));
        // major math
        assertEquals("math",jsonPath.getString("students[0].major"));
        //emailAddress mark@email.com
        assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));
        // companyName Cydeo
        assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));
        // street 777 5th Ave
        assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));
        // zipcode 33222
        assertEquals(33222,jsonPath.getInt("students[0].company.address.zipCode"));

    }

    /*
    Task
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    and Date header is exist
    And Server header is envoy
    And Verify all the batch number is 22
     */

    @DisplayName("Get the students batch 22 with path param")
    @Test
    public void Test(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("batch", 22)
                .when()
                .get("/student/batch/{batch}");
        // verify status code is 200
        assertEquals(200,response.statusCode());
        //verify the content type
        assertEquals("application/json;charset=UTF-8",response.contentType());

        //and Date header is exist
        assertTrue(response.headers().hasHeaderWithName("Date"));
        //And Server header is envoy
        assertEquals("envoy",response.header("Server"));
        //And Verify all the batch number is 22
        // make json path object
        JsonPath jsonPath = response.jsonPath();
        assertEquals(22,jsonPath.getInt("students[0].batch"));


    }
}
