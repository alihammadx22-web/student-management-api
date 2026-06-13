package com.hammad.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hammad.backend.model.Student;

public interface StudentRepo extends JpaRepository <Student, Integer>  {
List<Student> findByMarksGreaterThan(int marks);
List<Student> findByName(String name);
List<Student> findByMarksLessThan(int marks);
}
