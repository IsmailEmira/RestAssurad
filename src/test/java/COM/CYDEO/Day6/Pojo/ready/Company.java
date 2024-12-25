
package COM.CYDEO.Day6.Pojo.ready;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "companyId",
    "companyName",
    "startDate",
    "title",
    "address"
})
@Data
public class Company {

    @JsonProperty("companyId")
    public Integer companyId;
    @JsonProperty("companyName")
    public String companyName;
    @JsonProperty("startDate")
    public String startDate;
    @JsonProperty("title")
    public String title;
    @JsonProperty("address")
    public Address address;

}
