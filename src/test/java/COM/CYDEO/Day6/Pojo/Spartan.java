package COM.CYDEO.Day6.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Getter
@Data
@JsonIgnoreProperties (value ="id",allowSetters = true )
public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;


    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "gender='" + gender + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }



}
