package in.dminc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentJdbc {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;

    @Override
    public String toString() {
        return "StudentJdbc[" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ']';
    }
}
