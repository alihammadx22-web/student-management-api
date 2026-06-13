package com.hammad.backend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hammad.backend.model.Student;
import com.hammad.backend.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return service.getStudents();
    }

    @GetMapping("/students/marks/greater/{marks}")
    public List<Student> getStudentsByMarksGreaterThan(@PathVariable int marks) {
        return service.getStudentsByMarksGreaterThan(marks);
    }

    @GetMapping("/students/marks/less/{marks}")
    public List<Student> getStudentsByMarksLessThan(@PathVariable int marks) {
        return service.getStudentsByMarksLessThan(marks);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@Valid @RequestBody Student student) {
        return service.createStudent(student);
    }

    @PutMapping("/students/{rollNo}")
    public Student updateStudent(@PathVariable int rollNo, @Valid @RequestBody Student student) {
        student.setRollNo(rollNo);
        return service.updateStudent(rollNo, student);
    }

    @GetMapping("/students/{rollNo}")
    public Student getStudentByRollNo(@PathVariable int rollNo) {
        return service.getStudentByRollNo(rollNo);
    }

    @DeleteMapping("/students/{rollNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int rollNo) {
        service.deleteStudent(rollNo);
    }
}

