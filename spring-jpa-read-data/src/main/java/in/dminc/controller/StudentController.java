package in.dminc.controller;

import in.dminc.model.Students;
import in.dminc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Students>> getStudents(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "100") int pageSize
    ) {
        System.out.println("inside get students..!!");
        Page<Students> studentsPage = studentService.getStudents(pageNumber, pageSize);
        return ResponseEntity.ok(studentsPage);
        //        List<Students> students = studentsPage.getContent();
        //        return ResponseEntity.ok(students);
    }
}
