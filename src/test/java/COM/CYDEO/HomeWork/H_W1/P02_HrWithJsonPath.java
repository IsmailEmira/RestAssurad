package COM.CYDEO.HomeWork.H_W1;

import COM.CYDEO.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {
    @DisplayName("Get all /countries")
    @Test
    public void test1() {
        Response response = get("/countries");

        assertEquals(200, response.statusCode());

        // create jsonPath object
        JsonPath jsonPath = response.jsonPath();

        // get me 3rd country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " + jsonPath.getString("items[2].country_name"));

        //get me 3rd and 4th country name
        System.out.println("jsonPath.getString(\"items[2,3].country_name\") = " + jsonPath.getString("items[2,3].country_name"));

        //get me all country name where region_id is 2
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("list = " + list);

    }

    /*
    Given aacept type is application/json
    And query param limit is 200
    When user send request /employees
    Then user should see .....
     */

    @DisplayName("Get all /employees?limit=200 with JsonPath")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("limit", 200)
                .when()
                .get("/employees");

        // verify status code
        assertEquals(200, response.statusCode());

        // create jsonPath object
        JsonPath jsonPath = response.jsonPath();

        // get all emails from response
        System.out.println("jsonPath.getList(\"items.email\") = " + jsonPath.getList("items.email"));
        // get all emails who is working as IT_PROG
        System.out.println("jsonPath.getList(\"items.findAll {job_id==IT_PROG}.email\") = " + jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email"));
        //get me all employees first names whose salary is more than 10000
        System.out.println("jsonPath.getList(\"items.findAll {it.first_name>10000}.salary\") = " + jsonPath.getList("items.findAll {it.salary>10000}.first_name"));
        //get me all information from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}\") = " + jsonPath.getString("items.max {it.salary}"));
        //get me firstname from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}.first_name\") = " + jsonPath.getString("items.max {it.salary}.first_name"));
        //get me firstname from response who has min salary
        System.out.println("jsonPath.getString(\"items.min {it.salary}first_name\") = " + jsonPath.getString("items.min {it.salary}first_name"));


    }

    /*
    Task:-
    Given
       accept type is application/json
    When
        user sends get request to /locations
    Then
        response status code must be 200
        content type equals to application/json
        get the second city with Jsonpath
        get all country ids
        get all city where their country id is uk
     */

    @DisplayName("Get all /locations with JsonPath")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/locations");

        // verify the status code is 200
        assertEquals(200,response.statusCode());

        // verify content type equals to application/json
        assertEquals("application/json",response.contentType());

        // make jsonpath object
        JsonPath jsonPath = response.jsonPath();

        //get the second city with Jsonpath
        String SecondCity = jsonPath.getString("items[1].city");
        System.out.println("SecondCity = " + SecondCity);
        // get all country ids
        List<String> list = jsonPath.getList("items.country_id");
        System.out.println("list = " + list);
        // get all city where their country id is uk
        System.out.println("jsonPath.getString(\"items.findAll {it.city=='UK'}.country_id\") = " + jsonPath.getString("items.findAll {it.city=='UK'}.country_id"));


    }
}
