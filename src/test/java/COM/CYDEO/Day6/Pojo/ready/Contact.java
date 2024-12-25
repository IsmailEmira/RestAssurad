
package COM.CYDEO.Day6.Pojo.ready;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contactId",
    "emailAddress",
    "permanentAddress",
    "phone"
})
@Data
public class Contact {

    @JsonProperty("contactId")
    public Integer contactId;
    @JsonProperty("emailAddress")
    public String emailAddress;
    @JsonProperty("permanentAddress")
    public String permanentAddress;
    @JsonProperty("phone")
    public String phone;

}
