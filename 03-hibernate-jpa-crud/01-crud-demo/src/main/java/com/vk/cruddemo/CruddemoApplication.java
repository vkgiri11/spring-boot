package com.vk.cruddemo;

import com.vk.cruddemo.dao.StudentDao;
import com.vk.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    // from the Spring boot Framework
    // Executed after the Spring Beans have been loaded
    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao) {

        return runner -> {
            // createStudent(studentDao);
            // createMultipleStudents(studentDao);

            //readStudent(studentDao);

            //queryStudent(studentDao);
            //queryStudentByLastName(studentDao);

            //updateStudent(studentDao);

            //deleteStudent(studentDao);
            deleteAllStudent(studentDao);
        };
    }

    private void createStudent(StudentDao studentDao) {

        // create the student object
        System.out.println("Creating new student object.....");
        Student tempStudent = new Student("Paul", "Wilkis", "paul.wilkis@vk.com");

        // save the student object
        System.out.println("Saving the student.....");
        studentDao.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student, generated id: " + tempStudent.getId());
    }

    private void createMultipleStudents(StudentDao studentDao) {

        // create multiple student object
        System.out.println("Creating 3 student object.....");
        Student tempStudent1 = new Student("David", "Burns", "david.burns@vk.com");
        Student tempStudent2 = new Student("Keith", "Sarah", "keith.sarah@vk.com");
        Student tempStudent3 = new Student("Brenda", "Summers", "brenda.summers@vk.com");

        // save multiple student object
        System.out.println("Saving the students.....");
        studentDao.save(tempStudent1);
        studentDao.save(tempStudent2);
        studentDao.save(tempStudent3);

        // display id of the saved students
        System.out.println("Saved student, generated id: " + tempStudent1.getId());
        System.out.println("Saved student, generated id: " + tempStudent2.getId());
        System.out.println("Saved student, generated id: " + tempStudent3.getId());
    }

    private void readStudent(StudentDao studentDao) {

        // create the student object
        System.out.println("Creating new student object.....");
        Student tempStudent = new Student("Walter", "Jones", "walter.jones@vk.com");

        // save the student object
        System.out.println("Saving the student.....");
        studentDao.save(tempStudent);

        // display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student, generated id: " + theId);

        // retrieve student based on the id: primary key
        System.out.println("Retrieving student with id: " + theId);
        Student myStudent = studentDao.findById(theId);

        // display student
        System.out.println("Found the student: " + myStudent);
    }

    private void queryStudent(StudentDao studentDao) {

        // get a list of students
        List<Student> theStudents = studentDao.findAll();

        // display the list of students
        for (Student tempStudent : theStudents)
            System.out.println(tempStudent);
    }

    private void queryStudentByLastName(StudentDao studentDao) {

        // get a list of students
        List<Student> theStudents = studentDao.findByLastName("Burns");

        // display the list of students
        for (Student tempStudent : theStudents)
            System.out.println(tempStudent);
    }

    private void updateStudent(StudentDao studentDao) {

        // retrieve student based on the id: primary key
        int studentId = 3;
        System.out.println("Getting student with id: " + studentId);
        Student myStudent = studentDao.findById(studentId);

        // change last name
        System.out.println("Updating Student....");
        myStudent.setLast_name("Carter");

        // update the student
        studentDao.update(myStudent);

        // display the updated student
        System.out.println("Updated student: " + myStudent);
    }

    private void deleteStudent(StudentDao studentDao) {

        int studentId = 1;

        System.out.println("Deleting student with id: " + studentId);

        studentDao.delete(studentId);
    }

    private void deleteAllStudent(StudentDao studentDao) {

        System.out.println("Deleting all students....");

        int numRowsDeleted = studentDao.deleteAll();

        System.out.println("Deleted row count: " + numRowsDeleted);
    }

}
