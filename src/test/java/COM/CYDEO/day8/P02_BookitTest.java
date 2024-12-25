package COM.CYDEO.day8;

import COM.CYDEO.utilities.BookitTesdtBase;
import COM.CYDEO.utilities.BookitUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_BookitTest extends BookitTesdtBase {

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4MiIsImF1ZCI6InN0dWRlbnQtdGVhbS1tZW1iZXIifQ.zIcFXhVng5REMvXmUGrJRSPMp87ImMqxVoM6ofeDpZA";

    @DisplayName("Get /api/campuses")
    @Test
    public void test1(){

        System.out.println("accessToken = " + accessToken);
        given()
                .accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);


    }

    @DisplayName("Get /api/campuses")
    @Test
    public void test2() {

        given().accept(ContentType.JSON)
                .header("Authorization", BookitUtils.getToken("email", "password"))
                .when()
                .get("/api/users/me").prettyPeek()
                .then().statusCode(200);


    }

}
