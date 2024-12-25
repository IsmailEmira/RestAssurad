package COM.CYDEO.Day3;

import COM.CYDEO.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HrTestBase {

    @DisplayName("Get Request to countries with using Response Path")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

       // response.prettyPrint();

        // print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        // print hasMore
        System.out.println("response.path(\"hasMOre\") = " + response.path("hasMOre"));
        //print second country name
        System.out.println("response.path(\"[1].country_name\") = " + response.path("item[1].country_name"));
        // print 4th element country name
        System.out.println("response.path(\"[3].country_name\") = " + response.path("item[3].country_name"));
        //print 3th element href
        System.out.println("response.path(\"item[2].links[0].href\") = " + response.path("item[2].links[0].href"));
        //get all countries names
        List<String> allCountriesNames = response.path("items.country_name");
        System.out.println("allCountriesNames = " + allCountriesNames);

        //verify all region_ids equals to 2
        List<Integer> allRegionsIDs = response.path("items.region_id");

        for (Integer id:allRegionsIDs){
            assertEquals(2,id);

        }
    }
}
