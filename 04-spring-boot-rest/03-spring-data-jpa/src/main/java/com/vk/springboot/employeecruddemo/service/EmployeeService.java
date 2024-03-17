package com.vk.springboot.employeecruddemo.service;

import com.vk.springboot.employeecruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deletebyId(int theId);
}
