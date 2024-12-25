package COM.CYDEO.day7;

import COM.CYDEO.Day6.Pojo.Student;
import COM.CYDEO.Day6.Pojo.Students;
import COM.CYDEO.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserializationPojo extends CydeoTestBase {

     /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    and Date header is exist
    And Server header is envoy
    And verify following
                 firstname mark
                 batch 13
                 major math
                 emailAddress mark@email.com
                 companyName Cydeo
                 street 777 5th Ave
                 zipcode 33222
     */


    @Test
    public void task1() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");

        // verify the status code
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        // Deserialization to students class
        Students students = jsonPath.getObject("", Students.class);
        // we deserialize everything to students class which is holding list of students
        System.out.println("students = " + students);
        Students student1 = students.getStudents().get(0);

        //if there is no path, we can use response.as method for deserialization
        //Students students1 = response.as(Students.class);

        //Deserialize to student class

        Student student = jsonPath.getObject("students[0]", Student.class);

        assertEquals("Mark", student.getFirstName());

        assertEquals(13, student.getBatch());

        assertEquals("math", student.getMajor());

        assertEquals("mark@email.com", student.getContact().getEmailAddress());

        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());

        assertEquals(33222, student.getCompany().getAddress().getZipCode());
    }


    @Test
    public void task2() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");
        // verify the status code
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        COM.CYDEO.Day6.Pojo.ready.Student student2 = jsonPath.getObject("students[0]", COM.CYDEO.Day6.Pojo.ready.Student.class);

        System.out.println("student.getJoinDate() = " + student2.getJoinDate());

        System.out.println("student2.getCompany().getStartDate() = " + student2.getCompany().getStartDate());

        System.out.println("student2.getCompany().getAddress().getState() = " + student2.getCompany().getAddress().getState());
    }
}
