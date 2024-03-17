package com.example.vk.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // show initial HTML form
    @GetMapping("/showForm")
    public String showForm() {

        // src/main/resources/templates/helloworld.html
        return "helloworld-form";
    }

    // process the form
    // when the form is submitted this model is executed
    @RequestMapping("/processForm")
    public String processForm() {

        return "helloworld";
    }

    @RequestMapping("/processFormWithData") // support any HTTP request, GET, POST....
    public String processFormWithData(HttpServletRequest req, Model model) {

        // read the request param, from the Html form
        String theName = req.getParameter("studentName");

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the messeage
        String msg = "Yo! " + theName;

        // add message to the model
        // name of the attribute in the view template, the actual value
        model.addAttribute("message", msg);

        return "helloworld";
    }

    // use the studentName HTML form field name from the form directly, and assign it to a variable
    @GetMapping("/processFormWithData2")
    public String processFormWithData2(@RequestParam("studentName") String theName, Model model) {

        // convert the data to all caps
        theName = theName.toUpperCase();

        // create the messeage
        String msg = "Hey! " + theName;

        // add message to the model
        // name of the attribute in the view template, the actual value
        model.addAttribute("message", msg);

        return "helloworld";
    }

}
