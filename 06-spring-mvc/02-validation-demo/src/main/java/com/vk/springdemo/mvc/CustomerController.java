package com.vk.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // pre process every String Form Data
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        // StringTrimmerEditor - defined in Spring API
        // removes whitespaces from string: leading and trailing. since the constructor is set to true, for empty string the value is null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    @GetMapping("/")
    public String showForm(Model theModel) {

        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @PostMapping("/processForm")
    // @Valid will tell the spring MVC to perform validation,                         contains the result of validation
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {
        //System.out.println("Last Name: |" + theCustomer.getLastName() + "|");

        System.out.println("Binding Results: " + theBindingResult);

        // if the validation has error, return to the form page
        if (theBindingResult.hasErrors()) {
            return "customer-form";
        }

        return "customer-confirmation";

    }
}
