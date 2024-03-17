package com.vk.demo.rest;

import com.vk.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    // define @PostConstruct to load the student data only once
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Max", "Byers"));
        theStudents.add(new Student("Dan", "Hearth"));
        theStudents.add(new Student("Julie", "Sarah"));
    }

    // define endpoint "/students" - returns a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        // jackson will convert the POJO into JSOM
        return theStudents;
    }

    // define endpoint "students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student not found with id: " + studentId);
        }

        return theStudents.get(studentId);
    }
}
