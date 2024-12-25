
package COM.CYDEO.Day6.Pojo.ready;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "addressId",
    "city",
    "state",
    "street",
    "zipCode"
})
@Data

public class Address {

    @JsonProperty("addressId")
    public Integer addressId;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("street")
    public String street;
    @JsonProperty("zipCode")
    public Integer zipCode;

}
