package com.vk.cruddemo;

import com.vk.cruddemo.dao.AppDAO;
import com.vk.cruddemo.entity.*;
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

    // since it is annotated with the Bean annotation, spring will inject App dao automatically
    // no need for autowired
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO theAppDAO) {

        return runner -> {

            createCourseAndStudents(theAppDAO);

        };
    }

    private void createCourseAndStudents(AppDAO theAppDAO) {

        Course tempCourse = new Course("JAVA 1-100 : Start your learning in Java");

        Student tempStudent1 = new Student("Creed", "Bratton", "creed.bratton@office.com");
        Student tempStudent2 = new Student("Meredith", "Palmer", "meredith.palmer@office.com");
        Student tempStudent3 = new Student("Phyllis", "Vance", "bob.vance@office.com");

        tempCourse.addStudents(tempStudent1);
        tempCourse.addStudents(tempStudent2);
        tempCourse.addStudents(tempStudent3);

        System.out.println("Saving the course: " + tempCourse);
        System.out.println("Associated Students: " + tempCourse.getStudents());

        theAppDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO theAppDAO) {

        int theId = 10;

        System.out.println("Deleting Course with id: " + theId);

        // delete course and reviews -> cascadeType.All
        theAppDAO.deleteCourseById(theId);

        System.out.println("Done!");

    }

    private void findCourseAndReviews(AppDAO theAppDAO) {

        int theId = 10;

        // retrieves both the course and reviews -> JOIN FETCH
        Course tempCourse = theAppDAO.findCourseAndReviewsByCourseId(theId);

        System.out.println(tempCourse);

        System.out.println(tempCourse.getReviews());

        System.out.println("Done!");

    }

    private void createCourseAndReviews(AppDAO theAppDAO) {

        // create course
        Course tempCourse = new Course("JAVA 1-100 : Start your learning in Java");

        // create reviews
        tempCourse.addReview(new Review("Great Course.....loved it!"));
        tempCourse.addReview(new Review("Nice one! Learned a lot"));
        tempCourse.addReview(new Review("Not an easy one, good effort"));

        // save the course -> will save the reviews
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        theAppDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO theAppDAO) {

        int theId = 10;

        System.out.println("Delete course with ID: " + theId);

        theAppDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO theAppDAO) {

        int theId = 10;
        System.out.println("Finding Course with id: " + theId);

        Course tempCourse = theAppDAO.findCourseById(theId);

        // update the instructor
        tempCourse.setTitle("Customer Relations");

        theAppDAO.update(tempCourse);

        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO theAppDAO) {

        int theId = 1;
        System.out.println("Finding Instructor with id: " + theId);

        Instructor tempInstructor = theAppDAO.findInstructorById(theId);

        // update the instructor
        tempInstructor.setLastName("Kapoor");

        theAppDAO.update(tempInstructor);
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO theAppDAO) {

        int theId = 1;
        System.out.println("Finding Instructor id: " + theId);

        Instructor tempInstructor = theAppDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("Instructor: " + tempInstructor);

        System.out.println("Associated courses: " + tempInstructor.getCourses());

    }

    private void findCoursesForInstructor(AppDAO theAppDAO) {

        int theId = 1;
        System.out.println("Finding Instructor id: " + theId);

        // only loads the instructor, does not loads the courses since they are lazy loaded
        Instructor tempInstructor = theAppDAO.findInstructorById(theId);

        System.out.println("Instructor: " + tempInstructor);

        // find courses for instructor
        System.out.println("Finding courses for instructor with id: " + theId);

        List<Course> courses = theAppDAO.findCoursesByInstructorId(theId);

        tempInstructor.setCourses(courses);

        System.out.println("Associated courses: " + tempInstructor.getCourses());
    }

    private void findInstructorWithCourses(AppDAO theAppDAO) {

        int theId = 1;
        System.out.println("Finding Instructor id: " + theId);

        // only loads the instructor, does not loads the courses since they are lazy loaded
        Instructor tempInstructor = theAppDAO.findInstructorById(theId);

        System.out.println("Instructor: " + tempInstructor);

        System.out.println("Associated Courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
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

        int theId = 1;
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
