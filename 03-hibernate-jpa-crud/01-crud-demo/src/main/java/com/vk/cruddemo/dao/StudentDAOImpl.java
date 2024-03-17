package com.vk.cruddemo.dao;

import com.vk.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDao {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    // transactional since we are performing an update on the database
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist((theStudent));
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        // The student is "FROM Student" is the name of JPA entity, not the name of database table
        // lastName is JPA entity from the java class, not the actual db column name
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by last_name desc", Student.class);

        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // create query
        // JPQL named params are prefixed with colon.  :theData is a kind of placeholder for dynamic params
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE last_name=:theData", Student.class);

        // set query parameter
        theQuery.setParameter("theData", lastName);

        // return query result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        // retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }
}
