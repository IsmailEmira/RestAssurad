package COM.CYDEO.utilities;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookitUtils {

    public static String getToken(String email,String password){
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //get the key out of json body

        String accessToken = jsonPath.getString("accessToken");

        return "Bearer " + accessToken;

    }

    /*

      the proper way to handle different token is you need to have each user
      type in your configuration.properties then one method where you pick userType and it will return token to you

     getToken( String userType) "TEAM_LEADER", "TEACHER"

     Switch(userType)
     case "TEAM_LEADER"
     String email = configurationReader.getProperty("team-leader-email");
     String email = ConfigurationReader.getProperty("team-leader-password)
     .
     .
     .
     .
     send request with given and email and password
     */
}
