package com.vk.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // setting up the mapping to InstructorDetail entity

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")     // in DB, foreign key is configured to reference this field in instructor_detail_table
    private InstructorDetail instructorDetail;    // the mapped by value in InstructorDetail class points to this

    // refers to the "instructor" property in Course Class
    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // add convenience methods for bi-directional relationship
    public void add(Course tempCourse) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(tempCourse);

        tempCourse.setInstructor(this);
    }

    @Override
    public String toString() {
        return "Instructor{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", instructorDetail=" + instructorDetail + '}';
    }
}


/*

The convenience method add(Course tempCourse) in the Instructor class is used to simplify the process of adding a course to an instructor and, more importantly,
to manage the bidirectional relationship between the Instructor and Course entities in a more structured way. Let me explain its use and compare it to how the code would look without such a convenience method:

With Convenience Method (add(Course tempCourse)):

Instructor instructor = new Instructor("John", "Doe", "johndoe@luv2code.com");
Course course = new Course("Introduction to Programming");
instructor.add(course); // Automatically establishes the relationship

// Save the entities using Hibernate or JPA
In this case, you simply call instructor.add(course) to add the course to the instructor's list of courses. This method internally takes care of setting the instructor for the course,
ensuring that both sides of the bidirectional relationship are properly maintained. It makes the code more readable and less error-prone.



Without Convenience Method:

Without the convenience method, you would need to manually manage the bidirectional relationship, which could lead to more verbose and error-prone code:

Instructor instructor = new Instructor("John", "Doe", "johndoe@example.com");
Course course = new Course("Introduction to Programming");
if (instructor.getCourses() == null) {
    instructor.setCourses(new ArrayList<>());
}
instructor.getCourses().add(course); // Manually add the course
course.setInstructor(instructor); // Manually set the instructor for the course

// Save the entities using Hibernate or JPA
In this case, you have to check if the courses list is null, initialize it if necessary, manually add the course to the list, and also set the instructor for the course.
This approach is more error-prone, less readable, and can lead to potential bugs if you forget to set one side of the relationship.



The convenience method simplifies the process of adding courses and helps ensure that the bidirectional relationship is correctly maintained without requiring the developer to perform these tasks manually.
It improves code readability and reduces the chances of introducing bugs related to the relationship management.

 */