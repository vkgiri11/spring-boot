package com.vk.springboot.employeecruddemo.dao;

import com.vk.springboot.employeecruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @RepositoryRestResource(path="members") // sp now endpoints are at /members insted of /employees which is by default
public interface EmployeeRespository extends JpaRepository<Employee, Integer> {
                                                     //Entity Type, Primary Key

    // that's it, no need to write any code
    //  all the methods like findAll, findById,..... are defined in the JPARepository itself
}
