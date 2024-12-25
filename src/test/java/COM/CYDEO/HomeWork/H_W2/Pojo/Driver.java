package COM.CYDEO.HomeWork.H_W2.Pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class Driver {
    @JsonProperty("givenName")
    private String name;
    @JsonProperty("familyName")
    private String lastName;
    @JsonProperty("nationality")
    private String nationality;
}
