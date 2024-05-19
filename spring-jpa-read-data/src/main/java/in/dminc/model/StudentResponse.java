package in.dminc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;
}
