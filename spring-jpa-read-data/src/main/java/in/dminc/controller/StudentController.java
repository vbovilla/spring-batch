package in.dminc.controller;

import in.dminc.model.Student;
import in.dminc.model.StudentRequest;
import in.dminc.model.StudentResponse;
import in.dminc.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> getStudents(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "100") int pageSize
    ) {
        System.out.println("inside get students..!!");
        Page<Student> studentsPage = studentService.getStudents(pageNumber, pageSize);
        return ResponseEntity.ok(studentsPage);
        //        List<Student> students = studentsPage.getContent();
        //        return ResponseEntity.ok(students);
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        ModelMapper mapper = new ModelMapper();
        Student student = mapper.map(studentRequest, Student.class);
        Student savedStudent = studentService.saveStudent(student);
        return mapper.map(savedStudent, StudentResponse.class);
    }
}
