package com.vk.springboot.thymeleafdemo.dao;

import com.vk.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Entity Type, Primary Key
public interface EmployeeRespository extends JpaRepository<Employee, Integer> {

    // that's it, no need to write any code
    //  all the methods like findAll, findById,..... are defined in the JPARepository itself

    // add a method to sort by first name
    public List<Employee> findAllByOrderByFirstNameAsc();
    //Spring data JPA will parse the method name, looks for a specific format and pattern
    //Create appropriate query behind the scenes
}
