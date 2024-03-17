package com.vk.springboot.employeecruddemo.dao;

import com.vk.springboot.employeecruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDAO {

    // define fields for entity
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDaoJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findALl() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute a query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        Employee theEmployee = entityManager.find(Employee.class, theId);

        return theEmployee;
    }

    // we dont use @Transactional at the DAO Layer, but use it at service layer
    @Override
    public Employee save(Employee theEmployee) {

        // if id == 0, then it will insert into the databse, else it will update in the database;
        // returns back the updatedEmployee [it has the updated id from the databse]
        Employee dbEmployee = entityManager.merge(theEmployee);

        return dbEmployee;
    }

    @Override
    public void deletebyId(int theId) {

        // find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // remove employee
        entityManager.remove(theEmployee);
    }
}
