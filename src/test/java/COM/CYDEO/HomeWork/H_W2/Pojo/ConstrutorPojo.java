package COM.CYDEO.HomeWork.H_W2.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstrutorPojo {

    private String constructorId;

    private String name;

    private String url;

    private String nationality;



}
