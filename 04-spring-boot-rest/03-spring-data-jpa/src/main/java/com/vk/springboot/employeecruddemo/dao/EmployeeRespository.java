package com.vk.springboot.employeecruddemo.dao;

import com.vk.springboot.employeecruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//Entity Type, Primary Key
public interface EmployeeRespository extends JpaRepository<Employee, Integer> {

    // that's it, no need to write any code
    //  all the methods like findAll, findById,..... are defined in the JPARepository itself
}
