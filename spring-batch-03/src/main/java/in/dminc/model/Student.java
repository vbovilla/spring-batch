package in.dminc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Long phoneNumber;

    @Override
    public String toString() {
        return "Student[" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", phoneNumber=" + phoneNumber +
            ']';
    }
}
