package com.springreact.controller;

import com.springreact.model.Student;
import com.springreact.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    // no value provided: will map to the root url of the application
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
    }

    @GetMapping("/student/{theId}")
    public Student getStudentById(@PathVariable Long theId) {
        return studentService.getStudentById(theId);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student theStudent) {
        return studentService.addStudent(theStudent);
    }

    @PutMapping("/update/{theId}")
    public Student updateStudent(@RequestBody Student theStudent,@PathVariable Long theId) {
        return studentService.updateStudent(theStudent, theId);
    }

    @DeleteMapping("/delete/{theId}")
    public void deleteStudent(@PathVariable Long theId) {
        studentService.deleteStudentById(theId);
    }

}
