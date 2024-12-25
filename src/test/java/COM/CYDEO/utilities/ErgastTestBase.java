package COM.CYDEO.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ErgastTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://ergast.com/api/f1";
    }
}
