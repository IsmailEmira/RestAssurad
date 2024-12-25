package COM.CYDEO.Day6;

import COM.CYDEO.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P01_HrDeserialization extends HrTestBase {

    /**
     * Craete a test called getLocation
     * 1. Send request to Get /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of map
     * print out Get First Location
     * print out Get First Location Links
     * print out Get All Location As List of Map
     * print out  First Location
     * print out  First Location ID
     * print out  First Location Country_Id
     * print out Get First Location link
     */

    @DisplayName("Get /Location to deserialization into Java collection")

    @Test
    public void testGetLocation() {
        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/locations")
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        System.out.println("====  Get First Location======");
        Map<String, Object> FirstMap = jsonPath.getMap("items[0]");
        System.out.println("FirstMap = " + FirstMap);
        System.out.println("FirstMap.get(\"location_id\") = " + FirstMap.get("location_id"));

        System.out.println("====   Get First Location Links ======");
        Map<String, Object> firstMapLinks = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstMapLinks = " + firstMapLinks);

        System.out.println("====  Get All Location As List of Map ======");
        List<Map<String,Object>> allLocations = jsonPath.getList("items");
         for (Map<String, Object> eachLocations : allLocations) {
             System.out.println(eachLocations);
        }

        System.out.println("====  Get First Location======");
        System.out.println("allLocations.get(0) = " + allLocations.get(0));

        System.out.println("====  Get First Location id======");
        System.out.println("allLocations.get(0).get(\"location_id\") = " + allLocations.get(0).get("location_id"));

        System.out.println("====  Get First Location Country_id ======");
        System.out.println("allLocations.get(0).get(\"country_id\") = " + allLocations.get(0).get("country_id"));

        System.out.println("====  Get First Location Link ======");
        System.out.println("allLocations.get(0).get(\"link\") = " + allLocations.get(0).get("links"));

        // we want to get href from first location what we need to do?
        List<Map<String,Object>> links = (List<Map<String, Object>>) allLocations.get(0).get("links");
        System.out.println("links = " + links);
        System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("href"));

    }
}
