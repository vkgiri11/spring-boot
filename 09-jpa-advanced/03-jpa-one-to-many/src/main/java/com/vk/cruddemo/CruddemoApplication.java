package com.vk.cruddemo;

import com.vk.cruddemo.dao.AppDAO;
import com.vk.cruddemo.entity.Course;
import com.vk.cruddemo.entity.Instructor;
import com.vk.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    // since it is annotated with the Bean annotation, spring will inject App dao automatically
    // no need for autowired
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO theAppDAO) {

        return runner -> {
            //createInstructor(theAppDAO);

            //findInstructor(theAppDAO);

            //deleteInstructor(theAppDAO);

            //findInstructorDetail(theAppDAO);

            //deleteInstructorDetail(theAppDAO);

            createInstructorWithCourses(theAppDAO);
        };
    }

    private void createInstructorWithCourses(AppDAO theAppDAO) {

        Instructor tempInstructor = new Instructor("Kelly", "Kapoor", "kelly.kapoor@office.com");

        InstructorDetail tempInstructorDetail = new InstructorDetail("speaking.com", "speaking");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Public Relation");
        Course tempCourse2 = new Course("Customer Service");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        System.out.println("Saving Instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());

        // save the instructor to DB -> also save the courses since cascading
        theAppDAO.save(tempInstructor);

        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO theAppDAO) {

        int theId = 3;

        System.out.println("Delete Instructor Detail with ID: " + theId);

        theAppDAO.deleteInstructorDetailById(theId);

        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO theAppDAO) {

        // get the instructor detail object
        int theId = 1;

        InstructorDetail tempInstructorDetail = theAppDAO.findInstructorDetailById(theId);

        System.out.println("Instructor Detail: " + tempInstructorDetail);

        // print the associated instructor
        System.out.println("Associated Instructor: " + tempInstructorDetail.getInstructor());

    }

    private void deleteInstructor(AppDAO theAppDAO) {

        int theId = 2;
        System.out.println("Delete instructor with ID: " + theId);

        theAppDAO.deleteInstructorById(theId);

        System.out.println("Done!");
    }

    private void findInstructor(AppDAO theAppDAO) {

        int theId = 2;
        System.out.println("Finding instructor with ID: " + theId);

        Instructor tempInstructor = theAppDAO.findInstructorById(theId);

        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Associated Instructor Detail: " + tempInstructor.getInstructorDetail());

    }

    private void createInstructor(AppDAO theAppDAO) {

        // create the instructor
        Instructor tempInstructor = new Instructor("Meredith", "Palmer", "meredith.palmer@office.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("drinking.com", "drinking");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor -> this will also the details object because of CascadeType.ALL
        System.out.println("Saving instructor: " + tempInstructor);

        theAppDAO.save(tempInstructor);

        System.out.println("Done!");
    }

}
