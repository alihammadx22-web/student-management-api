package com.hammad.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hammad.backend.exception.ResourceNotFoundException;
import com.hammad.backend.model.Student;
import com.hammad.backend.repository.StudentRepo;

@Service
public class StudentService {

    private final StudentRepo repo;

    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    public List<Student> getStudents() {
        return repo.findAll();
    }

    public List<Student> getStudentsByMarksGreaterThan(int marks) {
        return repo.findByMarksGreaterThan(marks);
    }

    public List<Student> getStudentsByMarksLessThan(int marks) {
        return repo.findByMarksLessThan(marks);
    }

    public List<Student> getStudentsByName(String name) {
        return repo.findByName(name);
    }

    public Student createStudent(Student student) {
        if (repo.existsById(student.getRollNo())) {
            throw new IllegalArgumentException(
                    "Student with roll number " + student.getRollNo() + " already exists");
        }
        return repo.save(student);
    }

    public Student updateStudent(int rollNo, Student student) {
        if (!repo.existsById(rollNo)) {
            throw new ResourceNotFoundException(
                    "Student with roll number " + rollNo + " was not found");
        }
        student.setRollNo(rollNo);
        return repo.save(student);
    }

    public Student getStudentByRollNo(int rollNo) {
        return repo.findById(rollNo)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student with roll number " + rollNo + " was not found"));
    }

    public void deleteStudent(int rollNo) {
        if (!repo.existsById(rollNo)) {
            throw new ResourceNotFoundException(
                    "Student with roll number " + rollNo + " was not found");
        }
        repo.deleteById(rollNo);
    }
}
