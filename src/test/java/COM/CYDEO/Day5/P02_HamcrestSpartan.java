package COM.CYDEO.Day5;

import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_HamcrestSpartan extends SpartanTestBase {


    /*
    Given accept type is json
    And path param id is 15
    When user send a get request to "api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json"
    And response payload values match the following:
    id is 15
    name is "Meta"
    gender is "Female"
    phone is 1938695106
     */

    @DisplayName("Get Single Sparatn with Hamcrest")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id",is(15),"name",is("Meta"),"gender",is("Female")
                        ,"phone",is(1938695106));
    }

    @DisplayName("Get Single Sparatn with Hamcrest")
    @Test
    public void test2() {
        given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .assertThat() // assertThat(), and these are syntactic sugar just to increase readability
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("id",is(15))
                .body("name",is("Meta"))
                .body("gender",is("Female"))
                .body("phone",is(1938695106));


    }

    @DisplayName("Get Single Sparatn with Hamcrest with LOGS")
    @Test
    public void test3() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 15)
                // .log().all() // to see request all information
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .log().ifValidationFails(LogDetail.ALL)// to see response all information, if validation fails
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(15), "name", is("Meta"), "gender", is("Female")
                        , "phone", is(1938695106))
                .extract().jsonPath();

        //below lines are assume
        // api actual data (id,name)
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        System.out.println("id = " + id);

        //expected data from database
        //using db utils, saving variables
        int expectedIdDb = 15;
        String expectedNameDb = "Meta";

        //compare api vs database
        // we can use hamcrest or junit5 assertion
        assertThat(id,is(expectedIdDb));
        assertThat(name,is(equalTo(expectedNameDb)));


    }
    /*
    How to Extract Data After doing validation with Then() and Hamcrest?
    -- extract () --> method will help us to store data after doing verification in following type
    response() --> to get response object ex: extract().response()
    OR
    jsonPath() --> to get response body as jsonpath object directly
    ex:- extract().jsonPath();


    why we need to extract, while we can complete all of the verification (status code, header,body)
    with then() and hamcrest matchers?

    --Assume that we are going to do verification against DB/UI. In that case, I need to get data from API
    After completing my api verification,
    So we need to sometimes list of names/ ids/ whatever field we have to check against to db or UI


    How To print Response pretty peek
    response.prettyPrint() (String) --> it is a printing response body into screen
    response.prettyPeek() (Response) --> it will print response into screen, returns and allow us to continue chaining.
     */


}
