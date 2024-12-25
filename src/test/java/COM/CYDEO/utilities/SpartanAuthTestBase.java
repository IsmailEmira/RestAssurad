package COM.CYDEO.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanAuthTestBase {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://3.90.35.207:7000";
        // Add authentication details here
    }
}
