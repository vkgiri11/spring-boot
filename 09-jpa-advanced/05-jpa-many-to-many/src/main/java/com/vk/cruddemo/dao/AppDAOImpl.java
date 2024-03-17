package com.vk.cruddemo.dao;

import com.vk.cruddemo.entity.Course;
import com.vk.cruddemo.entity.Instructor;
import com.vk.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define fields for the entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        // this will also save the instructor detail since we have Cascade.ALL
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {

        // this will also retrieve the instructor detail object
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // find the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        List<Course> courses = tempInstructor.getCourses();

        // break associations of all courses for the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null); // removes the instructor from courses
            // if we don't do this, we will get constraint violation
        }

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // find the instructor
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // removes the associated object reference
        // breaks the the bidirectional link

        // why this is required even after we changes the cascading type....
        // so once the instructor detail is deleted, think about the instructor table
        // it has a value that link both the tables, now if the value [of instructor_detail] in instructor is not set to null, it creates problem with how DB is configured

        /*
         While removing CascadeType.ALL removes the automatic cascading of all operations, including delete, it doesn't necessarily break the association between entities in memory.
         Even without CascadeType.ALL, Hibernate might still retain references to associated entities.
        Setting the association to null (tempInstructorDetail.getInstructor().setInstructorDetails(null)) is a manual step to explicitly de-associate the entities in memory.
        This ensures that when the remove operation is later performed on the entityManager, Hibernate knows to cascade the removal operation to the associated entity.
        So, even without CascadeType.ALL, manual disassociation becomes necessary to guarantee the removal of associated entities during a cascading delete operation.
        */
        tempInstructorDetail
                .getInstructor()
                .setInstructorDetail(null);

        // delete the instructor
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute the query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // Even with Fetch type Lazy -> this will retrieve both Instructor and Courses
        // JOIN Fetch is similar to EAGER Loading
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id= :data", Instructor.class);

        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        Course tempCourses = entityManager.find(Course.class, theId);

        entityManager.remove(tempCourses);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        // save the course and the associated review -> since CasacdeType.All
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id = :data", Course.class);

        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }
}
