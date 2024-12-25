package COM.CYDEO.utilities;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public abstract class SpartanTestBase {



    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://3.90.35.207:8000";

    }



}
