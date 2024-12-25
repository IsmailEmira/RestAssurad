package COM.CYDEO.Day6.Pojo;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class Students {

     private List<Students> students;

    private String firstName;

    private int batch;

    private String major;

    private Contact contact;

    private Company company;


}
