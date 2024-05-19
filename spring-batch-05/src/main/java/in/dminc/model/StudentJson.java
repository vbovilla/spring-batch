package in.dminc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentJson {
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private String gender;
    @JsonProperty("phone_number")
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
