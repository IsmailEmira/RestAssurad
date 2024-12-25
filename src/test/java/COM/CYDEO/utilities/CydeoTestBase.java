package COM.CYDEO.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class CydeoTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://3.86.13.225:8080";
    }
}
