package in.dminc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) //ignores properties from feed if they are not mapped in POJO
public class StudentJson {

    // JsonProperty names are case-sensitive -> id != ID, first_name!=FIRST_NAME, etc.
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("phone_number")
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
