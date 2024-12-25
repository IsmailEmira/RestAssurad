package COM.CYDEO.Day1;

import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    String url = "http://3.90.35.207:8000/api/spartans";

    /*
    when user send request to api/spartans endpoint
    Then user should be able to see status code is 200
    and print out response body into screen
     */

   @Test
    public void simpleGetRequest(){

        Response response = RestAssured.get(url);

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //verify that status code is 200
        int actualStatusCode = response.statusCode();

        //assert that is 200
        Assertions.assertEquals(200,actualStatusCode);

        // how to print json response body in cosole
        response.prettyPrint();


    }
}
