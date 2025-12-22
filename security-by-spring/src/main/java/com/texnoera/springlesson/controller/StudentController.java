package com.texnoera.springlesson.controller;

import com.texnoera.springlesson.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "Jane", "Test"),
            new Student(2L, "Jack", "Test2"));

    @GetMapping("/{studentId}")
    public Student getById(@PathVariable("studentId") Long id) {
        return STUDENTS.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " not found"));
    }

}
