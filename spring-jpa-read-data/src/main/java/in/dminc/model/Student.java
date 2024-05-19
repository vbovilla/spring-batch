package in.dminc.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "student_backup")
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Student[" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", gender='" + gender + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ']';
    }
}
