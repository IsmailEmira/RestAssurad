package COM.CYDEO.HomeWork.H_W1;
import COM.CYDEO.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01H_W extends HrTestBase {


    /*
    Task 1 :
- Given accept type is Json
- When users sends request to /countries/US
- Then status code is 200
- And Content - Type is application/json
- And response contains United States of America
     */

    @DisplayName("Get response /countries/US ")
    @Test
    public void Test1(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/countries/US");

        // verify the status code is 200

        assertEquals(200,response.statusCode());

        // verify the content type

        assertEquals("application/json",response.contentType());

        // verify the response contains United States of America

        assertTrue(response.body().asString().contains("United States of America"));

    }

    /*
    - Given accept type is Json
    - When users sends request to /employees/1
    - Then status code is 404
     */

    @DisplayName("Get request to /employees/1,status code is 404 ")
    @Test
    public void Test2(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/employees/1");

        // verify the status code is 404
        assertEquals(404,response.statusCode());
    }


    /*
    Task 3 :
- Given accept type is Json
- When users sends request to /regions/1
- Then status code is 200
- And Content - Type is application/json
- And response contains Europe
- And header should contains Date
- And Transfer-Encoding should be chunked
     */

    @DisplayName("Get request to /regions/1,code is 200 ")
    @Test
    public void Test3(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/regions/1");

        // verify the status code is 200
        assertEquals(200,response.statusCode());
        // verify the content type
        assertEquals("application/json",response.contentType());

        //- And response contains Europe
        assertTrue(response.body().asString().contains("Europe"));
        //- And header should contains Date
        assertTrue(response.headers().hasHeaderWithName("Date"));
        //- And Transfer-Encoding should be chunked
        assertEquals("chunked",response.header("Transfer-Encoding"));



    }


}
