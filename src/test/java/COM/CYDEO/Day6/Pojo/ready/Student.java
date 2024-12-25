
package COM.CYDEO.Day6.Pojo.ready;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "studentId",
    "admissionNo",
    "batch",
    "birthDate",
    "firstName",
    "gender",
    "joinDate",
    "lastName",
    "major",
    "password",
    "section",
    "subject",
    "contact",
    "company"
})
@Data
public class Student {

    @JsonProperty("studentId")
    public Integer studentId;
    @JsonProperty("admissionNo")
    public String admissionNo;
    @JsonProperty("batch")
    public Integer batch;
    @JsonProperty("birthDate")
    public String birthDate;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("gender")
    public String gender;
    @JsonProperty("joinDate")
    public String joinDate;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("major")
    public String major;
    @JsonProperty("password")
    public String password;
    @JsonProperty("section")
    public String section;
    @JsonProperty("subject")
    public String subject;
    @JsonProperty("contact")
    public Contact contact;
    @JsonProperty("company")
    public Company company;

}
