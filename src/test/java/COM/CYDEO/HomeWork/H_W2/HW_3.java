package COM.CYDEO.HomeWork.H_W2;

import COM.CYDEO.utilities.ZippoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HW_3 extends ZippoTestBase {

    /*
    TASK 1
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
post code is 22031
country is United States
country abbreviation is US
place name is Fairfax state is Virginia
     */

    @DisplayName("Get request to /us endpoint")
    @Test
    public void task1() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("zipcode", "22031")
                .when()
                .get("/us/{zipcode}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .header("Server", "cloudflare")
                .header("Report-To", notNullValue())
                .assertThat()
                .body("'post code'", is("22031"))
                .body("country", equalTo("United States"))
                .body("'country abbreviation'", equalTo("US"))
                .body("places[0].'place name'", equalTo("Fairfax"))
                .body("places[0].state", equalTo("Virginia"))
                .extract().jsonPath();


    }

    /*
    TASK 2
Given Accept application/json
And path zipcode is 50000
When I send a GET request to /us endpoint
Then status code must be 404
And content type must be application/json
     */

    @DisplayName("Get request to /us status code 404")
    @Test
    public void task2(){
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("zipcode",5000)
                .when()
                .get("/us/{zipcode}")
                .then()
                .statusCode(404)
                .contentType("application/json");

    }
    /*
    TASK 3
Given Accept application/json
And path state is va
And path city is fairfax
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information
country abbreviation is US
country United States
place name Fairfax
each places must contains fairfax as a value each post code must start with 22
     */

@DisplayName("Get request to /us path city is VA")
    @Test
    public void task3(){

    JsonPath jsonPath = given()
            .accept(ContentType.JSON)
            .and()
            .pathParam("state", "va")
            .pathParam("city", "fairfax")
            .when()
            .get("us/{state}/{city}")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .assertThat()
            .body("'country abbreviation'", equalTo("US"))
            .body("country", equalTo("United States"))
            .body("places[0].'place name'", equalTo("Fairfax"))
            .body("places.'post code'", everyItem(startsWith("22")))
            .extract().jsonPath();


}



}
