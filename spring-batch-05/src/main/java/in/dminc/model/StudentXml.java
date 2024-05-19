package in.dminc.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@XmlRootElement(name = "student")
public class StudentXml {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phoneNumber;


    public int getId() {
        return id;
    }

    @XmlElement(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    @XmlElement(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

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
