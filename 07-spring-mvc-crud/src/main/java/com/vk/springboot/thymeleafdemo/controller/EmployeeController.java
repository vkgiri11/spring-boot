package com.vk.springboot.thymeleafdemo.controller;

import com.vk.springboot.thymeleafdemo.entity.Employee;
import com.vk.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employee from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormAdd(Model theModel) {

        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    // extract the employee id from the url and attach to the variable theId
    public String showFormUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // get the employee
        Employee theEmployee = employeeService.findById(theId);

        // set employee in the model to prepoulate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to the form
        return "employees/employee-form";
    }

    // @ModelAttribute("employee") - the form data being passsed in due to data binding : the employee object from the form
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    // extract the employee id from the url and attach to the variable theId
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.deletebyId(theId);

        // send over to the form
        return "redirect:/employees/list";
    }

}
