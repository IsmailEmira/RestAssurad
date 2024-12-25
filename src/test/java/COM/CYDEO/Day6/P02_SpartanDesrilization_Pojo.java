package COM.CYDEO.Day6;
import COM.CYDEO.Day6.Pojo.Search;
import COM.CYDEO.Day6.Pojo.Spartan;
import COM.CYDEO.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_SpartanDesrilization_Pojo extends SpartanTestBase {

    @DisplayName("Get Single Spartan for deserliztion to Pojo (Spartan Class) ")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        /*
        {
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}

         */

        System.out.println("----------RESPONSE------------");
        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan.getId() = " + spartan.getId());
        System.out.println("spartan.getName() = " + spartan.getName());
        System.out.println("spartan.getGender() = " + spartan.getGender());

        //JsonPath
        System.out.println("-------JsonPath------------");
        JsonPath jsonPath = response.jsonPath();

        Spartan spartanJP = jsonPath.getObject("", Spartan.class);
        System.out.println("spartanJP.getId() = " + spartanJP.getId());
        System.out.println("spartanJP.getName() = " + spartanJP.getName());
        System.out.println("spartanJP.getGender() = " + spartanJP.getGender());
        System.out.println("spartanJP.getPhone() = " + spartanJP.getPhone());


    }

    @DisplayName("Get Spartans from search endpoint and deserialize to Pojo")
    @Test
    public void test2() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Spartan spartan = jsonPath.getObject("content[9]", Spartan.class);
        System.out.println("spartan = " + spartan);


    }

    @DisplayName("Get Spartans from endpoint for deserlization to Pojo")
    @Test
    public void test3() {

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        Search search = response.as(Search.class);

        System.out.println("search.getTotalElement() = " + search.getTotalElement());
        System.out.println("search.getContent().get(1) = " + search.getContent().get(1));

        //get me second spartan name
        System.out.println("search.getContent().get(1).getName() = " + search.getContent().get(1).getName());

    }


    @DisplayName("Get Spartans from endpoint for deserlization to Pojo")
    @Test
    public void test4() {

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Spartan> allSpartans = jsonPath.getList("content", Spartan.class);

        for (Spartan spartan : allSpartans) {
            System.out.println("spartan = " + spartan);
        }
        System.out.println("allSpartans.get(1).getId() = " + allSpartans.get(1).getId());

    }
}

