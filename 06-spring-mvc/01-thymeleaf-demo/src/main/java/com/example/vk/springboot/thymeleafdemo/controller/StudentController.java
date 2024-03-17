package com.example.vk.springboot.thymeleafdemo.controller;

import com.example.vk.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    // add these values from the application.poperties file
    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> progLangs;

    @Value("${opSys}")
    private List<String> opSystems;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a student object
        Student theStudent = new Student();

        // add student object to the model
        theModel.addAttribute("student", theStudent);

        // add the list of countries to the model
        theModel.addAttribute("countries", countries);

        // add list of programming languages to the model
        theModel.addAttribute("favProgLangs", progLangs);

        theModel.addAttribute("operatingSystems", opSystems);

        return "student-form";
    }

    @PostMapping("processStudentForm")
    public String processForm(@ModelAttribute("student") Student theStudent) {

        // log the input data
        System.out.println(theStudent.getFirstName() + " " + theStudent.getLastName());

        return "student-confirmation";
    }

}
