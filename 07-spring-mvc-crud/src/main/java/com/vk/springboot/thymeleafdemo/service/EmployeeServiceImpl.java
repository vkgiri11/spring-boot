package com.vk.springboot.thymeleafdemo.service;

import com.vk.springboot.thymeleafdemo.dao.EmployeeRespository;
import com.vk.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRespository employeeRespository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRespository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Employee findById(int theId) {

        // extra code for JPA Repository
        Optional<Employee> result = employeeRespository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Employee not found with id: " + theId);
        }

        return theEmployee;
    }

    // can remove transactional, since JPA repository provides this functionality
    //@Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRespository.save(theEmployee);
    }

    @Override
    public void deletebyId(int theId) {
        employeeRespository.deleteById(theId);
    }
}
