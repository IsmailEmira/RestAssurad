package COM.CYDEO.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ZippoTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://api.zippopotam.us";

    }
}
