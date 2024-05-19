package in.dminc.service;

import in.dminc.model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    List<Student> studentList = new ArrayList<>();
    int currentPageNumber = 0;
    int recordsPerPage = 100;

    public List<Student> apiCallToGetStudents() {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("getting students " + recordsPerPage + " from page: " + currentPageNumber);
        Map response = restTemplate.getForObject("http://localhost:9090/api/v1/students?pageNumber=" + currentPageNumber + "&pageSize=" + recordsPerPage, Map.class);
        if (null == response) {
            return Collections.emptyList();
        }

        Boolean isEmpty = (Boolean) response.get("empty");
        if (isEmpty) {
            return Collections.emptyList();
        }

        currentPageNumber = (Integer) response.get("number");
        currentPageNumber++;

        return (List<Student>) response.get("content");
    }

    public Student retrieveStudents() {
        if (null == studentList || studentList.isEmpty()) {
            List<Student> results = apiCallToGetStudents();
            ModelMapper mapper = new ModelMapper();
            Student[] student = mapper.map(results, Student[].class);
            studentList.addAll(Arrays.asList(student));
        }

        if (null != studentList && !studentList.isEmpty()) {
            return studentList.remove(0);
        }

        return null;
    }
}
