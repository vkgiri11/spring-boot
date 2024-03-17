package com.vk.springboot.employeecruddemo.dao;

import com.vk.springboot.employeecruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findALl();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deletebyId(int theId);
}
