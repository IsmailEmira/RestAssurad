package COM.CYDEO.HomeWork.H_W2;

import COM.CYDEO.HomeWork.H_W2.Pojo.Driver;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.Matchers.*;
import COM.CYDEO.utilities.ErgastTestBase;

public class Hw_4  extends ErgastTestBase {

/*
TASK 1 : Solve same task with 4 different way
- Given accept type is json
- And path param driverId is alonso
- When user send request /drivers/{driverId}.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8
- And total is 1
- And givenName is Fernando
- And familyName is Alonso
- And nationality is Spanish
 */
// JsonPath approach :-
    @Test
    public void task1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("driverId","alonso")
                .when()
                .get("/drivers/{driverId}.json");
        assertEquals(200,response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());
        // we saved our response as jsonpath
        JsonPath jsonPath = response.jsonPath();
        int total = jsonPath.getInt("MRData.total");
        String name = jsonPath.getString("MRData.DriverTable.Drivers[0].givenName");
        String familyName = jsonPath.getString("MRData.DriverTable.Drivers[0].familyName");
        String nationality = jsonPath.getString("MRData.DriverTable.Drivers[0].nationality");


        assertEquals(1, total);
        assertEquals("Fernando",name);
        assertEquals("Alonso",familyName);
        assertEquals("Spanish",nationality);
    }
     //Hamcrest approach
    @Test
    public void task1_hamcrest() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .assertThat()
                .body("MRData.total", equalTo("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", equalTo("Fernando"))
                .body("MRData.DriverTable.Drivers[0].familyName", equalTo("Alonso"))
                .body("MRData.DriverTable.Drivers[0].nationality", equalTo("Spanish"))
                .extract().jsonPath();


        // Convert Driver information to Java structure
        Map<String, Object> driverMap = jsonPath.getMap("MRData.DriverTable.Drivers[0]");

        //assertion on Map Values
        assertEquals("Fernando",driverMap.get("givenName"));
        assertEquals("Alonso",driverMap.get("familyName"));
        assertEquals("Spanish",driverMap.get("nationality"));

        //print all names

    }

    @Test
    public void task1_Pojo(){

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        Driver driver = jsonPath.getObject("MRData.DriverTable.Drivers[0]", Driver.class);

        System.out.println("driver = " + driver);


    }

   }









