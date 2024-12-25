package COM.CYDEO.HomeWork.H_W2;

import COM.CYDEO.HomeWork.H_W2.Pojo.ConstrutorPojo;
import COM.CYDEO.HomeWork.H_W2.Pojo.Driver;
import COM.CYDEO.utilities.ConstructorTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import COM.CYDEO.utilities.ErgastTestBase;

public class Hw4_2 extends ConstructorTestBase {

    /*
    - Given accept type is json
- When user send request /constructorStandings/1/constructors.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8
- And total is 17
- And limit is 30
- And each constructor has constructorId
- And constructor has name
- And size of constructor is 17
- And constructor IDs has “benetton”, “mercedes”,”team_lotus”

     */

    //JsonPath approach
    @Test
    public void task1() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json");
        // assertion status code , content type
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());

        JsonPath jsonPath = response.jsonPath();
        //- And total is 17
        int total = jsonPath.getInt("MRData.total");
        assertEquals(total, 17);
        System.out.println("total = " + total);
        // - And limit is 30
        int limit = jsonPath.getInt("MRData.limit");
        assertEquals(limit, 30);
        System.out.println("limit = " + limit);
        //- And each constructor has constructorId
        List<String> constructorList = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        System.out.println("constructorList = " + constructorList);
        //- And constructor has name
        List<String> nameList = jsonPath.getList("MRData.ConstructorTable.Constructors.name");
        System.out.println("nameList = " + nameList);

    }

    //HamCrest Matchers
    @Test
    public void task2() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .assertThat()
                .body("MRData.total", is("17"))
                .body("MRData.limit", equalTo("30"))
                .body("MRData.ConstructorTable.Constructors.constructorId", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.name", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors", hasSize(17))
                .body("MRData.ConstructorTable.Constructors.constructorId", hasItems("benetton", "mercedes", "team_lotus"))
                .extract().jsonPath();

        // print all names of constructors
        System.out.println("jsonPath.getList(\"MRData.ConstructorTable.Constructors.name\") = " + jsonPath.getList("MRData.ConstructorTable.Constructors.name"));

    }

    @Test
    public void task3() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .assertThat()
                .body("MRData.total", is("17"))
                .body("MRData.limit", equalTo("30"))
                .extract().jsonPath();

        // convert constructor information to java structure
        List<Map<String, Object>> constructorMap = jsonPath.getList("MRData.ConstructorTable.Constructors");

        // assert each constructor has a constructorId
        for (Map<String, Object> constructor : constructorMap) {
            assertNotNull(constructor.get("constructorId"));
            assertNotNull(constructor.get("name"));;
        }
        // assert constructor size is 17
        assertEquals(17, constructorMap.size());
        // assert constructor IDs are "benetton", "mercedes", "team_lotus"
        List<String> listOfNames = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
        assertTrue(listOfNames.contains("benetton"));
        assertTrue(listOfNames.contains("mercedes"));
        assertTrue(listOfNames.contains("team_lotus"));
    }

    @Test
    public void task4() {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();


        //And total is 17
        int total= jsonPath.getInt("MRData.total");
        assertEquals(17,total);
        //And limit is 30
        int limit = jsonPath.getInt("MRData.limit");
        assertEquals(30,limit);
        //- And each constructor has constructorId
        //- And constructor has name
        List<ConstrutorPojo> constructors = jsonPath.getList("MRData.ConstructorTable.Constructors",ConstrutorPojo.class);
        for (ConstrutorPojo constructor : constructors){
            assertNotNull(constructor.getConstructorId());
            assertNotNull(constructor.getName());
        }

        //- And size of constructor is 17
        assertEquals(17,constructors.size());
        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        List<String> constructorId = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
            assertTrue(constructorId.contains("benetton"));
            assertTrue(constructorId.contains("mercedes"));
            assertTrue(constructorId.contains("team_lotus"));
        }



    }


