package COM.CYDEO.day8;

import COM.CYDEO.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("Get /api/spartans as GUEST Expect -->401")
@Test
    public void test1(){
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(401)
                .log().all();
    }
    @DisplayName("Get /api/spartans as USER Expect --> 200")
    @Test
    public void test2(){
        given()
                .accept(ContentType.JSON)
                .auth().basic("user","user")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();
    }
    @DisplayName("DELETE /api/spartans/{id} as EDITOR --> 403")
    @Test
    public void test3(){
        given()
                .accept(ContentType.JSON)
                .pathParam("id",200)
                .auth().basic("editor","editor")
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403)
                .body("error",is("Forbidden"));


    }



}
