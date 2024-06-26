package in.dminc.service;

import in.dminc.model.Student;
import in.dminc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Page<Student> getStudents(int pageNumber, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
