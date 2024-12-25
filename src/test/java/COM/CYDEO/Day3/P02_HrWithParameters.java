package COM.CYDEO.Day3;

import COM.CYDEO.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithParameters extends HrTestBase {

    /*
    Given accept type is Json
    And parameters: q =  {"region_id":2}
    When users send a Get request to "/countries"
    Then status code is 200
    And Content type is application/json
    And Payload should contain "United States of America"
     */

    @DisplayName("Get a request to Countries -\"United States of America\" ")

    @Test
    public void HrTest1(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        response.prettyPrint();
         // Verify status code
        assertEquals(200,response.statusCode());

        //And Content type is application/json
        assertEquals("application/json",response.contentType());
        //    And Payload should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));

    }


}
