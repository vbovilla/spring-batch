package in.dminc.service;

import in.dminc.model.StudentJdbc;
import in.dminc.model.StudentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {

    public StudentResponse writeStudents(StudentJdbc studentJdbc){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:9090/api/v1/students", studentJdbc, StudentResponse.class);
    }
}
