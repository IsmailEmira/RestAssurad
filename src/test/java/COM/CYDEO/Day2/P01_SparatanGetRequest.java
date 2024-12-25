package COM.CYDEO.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class P01_SparatanGetRequest {

    String url = "http://3.90.35.207:8000";


    /*
    Given content type is application/json
    when user sends Get request /api/spartans endpoint
    Then status code should be 200
    And Content type should be application/json
     */

    @Test
    public void getAllSpartans() {

         Response response = RestAssured.given()
                 .accept(ContentType.JSON) // hey api please send me json response
           .when()
                .get(url + "/api/spartans");

        //print response body
       // response.prettyPrint();

        int actualStatusCode = response.getStatusCode();

        Assertions.assertEquals(200,actualStatusCode);

        // how to get response content type header

        String actualContentType = response.contentType();

        System.out.println(actualContentType);

        // assert the content type
        Assertions.assertEquals("application/json",actualContentType);

        // how to get connection header value?
        // if we want to get any response header value, we can use header ("headerName")
        // method from response object it will return header value as string
        System.out.println(response.header("content-type"));
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Date"));

        // how to verify header exist?
        //hasHeaderWithName method help us to verify header exists or not
        // it has useful for dynamic header values like Date, we are only verifiying header exist or not, not checking any value.
        boolean isDate = response.headers().hasHeaderWithName("Transfer-Encoding");

        Assertions.assertTrue(isDate);



    }


    /*
    Given content type is application/json
    when user sends Get request /api/spartans/3 endpoint
    Then status code should be 200
    And Content type should be application/json
    And response body needs to contains Fidole
     */

    @Test
    public void getOneSpartans(){

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans/3");


         // verify status code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200,actualStatusCode);
        // verify content type is json
        String actualContent = response.contentType();

        System.out.println(actualContent);

        Assertions.assertEquals("application/json",actualContent);

        response.prettyPrint();
        // verify body contains "Fidole"
        Assertions.assertTrue( response.body().asString().contains("Fidole"));

        /*
        This is not a good way to make assertions. In this way we are just converting response to string and with help
        of the string contains we are just looking into response. But we should be able to get json "name"
        Key value then verify that one is equal to "fidole"
         */



    }

    /*
    Given no headers provided
    When Users send Get request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain Date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */

    @Test
    public void getHelloText(){

        Response response = RestAssured
                .when()
                .get(url + "/api/hello");

        // Assertion the status code

        Assertions.assertEquals(200,response.getStatusCode());


        // Assert the Content
         Assertions.assertEquals("text/plain;charset=UTF-8",response.getContentType());


         //  And header should contain Date
        boolean date = response.headers().hasHeaderWithName("Date");
       // And Content-Length should be 17
        Assertions.assertEquals("17",response.header("Content-Length"));
       // And body should be "Hello from Sparta"
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));
        response.prettyPrint();


    }


}
