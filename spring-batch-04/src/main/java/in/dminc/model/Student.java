package in.dminc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {


    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Student[" +
            "studentId=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", emailAddress='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", phoneNumber=" + phoneNumber +
            ']';
    }
}
