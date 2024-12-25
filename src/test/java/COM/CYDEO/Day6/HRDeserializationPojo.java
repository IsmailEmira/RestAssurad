package COM.CYDEO.Day6;

import COM.CYDEO.Day6.Pojo.Employee;
import COM.CYDEO.Day6.Pojo.Region;
import COM.CYDEO.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HRDeserializationPojo extends HrTestBase {
    @DisplayName("Get regions to deserializations to Pojo - Lombok - Json Property")
    @Test
    public void test1(){
        JsonPath jsonPath = get("/regions")//.prettyPeek()
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //get first region from items array and convert it to Region class
        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);

        System.out.println("region1.getLinks().get(0) = " + region1.getLinks().get(0));
        System.out.println("region1.getRegionId(0) = " + region1.getRegionId());
        System.out.println("region1.getRegionName() = " + region1.getRegionName());

    }

    @DisplayName("Get First employee ")
    @Test
    public void test2(){
        JsonPath jsonPath = get("/employees")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);
        System.out.println("employee1 = " + employee1);

    }




}
