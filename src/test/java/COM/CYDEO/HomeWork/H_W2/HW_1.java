package COM.CYDEO.HomeWork.H_W2;

import COM.CYDEO.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HW_1 extends HrTestBase {

    /*
    - Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
And Region_id is 2
     */

    @DisplayName("Get the country name using Path parameter")
    @Test
    public void task1() {
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("country_id", "US")
                .when()
                .get("/countries/{country_id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .assertThat()
                .body("country_id", equalTo("US"))
                .body("country_name", equalToIgnoringCase("United States of America"))
                .body("region_id", equalTo(2));

    }

    /*
    - Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
     */

    @DisplayName("Get response from request to /employees")
    @Test
    public void task2() {
        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .assertThat()
                .body("items.job_id", everyItem(startsWith("SA")))
                .body("items.department_id", everyItem(equalTo(80)))
                .body("count", equalTo(25));

    }

    /*
    - Given accept type is Json
  - Query param value q={“region_id":3}
  - When users sends request to /countries
  - Then status code is 200
  - And all regions_id is 3
  - And count is 6
  - And hasMore is false
  - And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */
    @DisplayName("Get all regions_id is 3")
    @Test
    public void task3() {
        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q", "{“region_id\":3}")
                .when()
                .get("/countries")
                .then()
                .statusCode(200)
                .assertThat()
                .body("items.region_id",everyItem(equalTo(6)))
                .body("count",is(6))
                .body("hasMore",equalTo(false))
                .body("items.country_name",containsInRelativeOrder("Australia,China,India,Japan,Malaysia,Singapore"));




    }
}
