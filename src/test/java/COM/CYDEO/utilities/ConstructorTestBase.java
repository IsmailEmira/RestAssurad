package COM.CYDEO.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ConstructorTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://ergast.com/api/f1";
    }




}
