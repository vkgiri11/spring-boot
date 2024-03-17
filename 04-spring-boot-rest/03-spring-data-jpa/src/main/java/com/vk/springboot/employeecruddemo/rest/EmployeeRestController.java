package com.vk.springboot.employeecruddemo.rest;

import com.vk.springboot.employeecruddemo.entity.Employee;
import com.vk.springboot.employeecruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "employees" and return a list of all employess
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }

        return theEmployee;
    }

    // add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // if an id is passed in json, ignore that...set id to 0
        // to force a save of new item, instead of updating
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        // this has updated id from the database
        return dbEmployee;
    }

    // update employee
    @PutMapping("/employees/{putEmployee}")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }

        employeeService.deletebyId(employeeId);

        return "Deleted employee with id: " + employeeId;
    }
}
