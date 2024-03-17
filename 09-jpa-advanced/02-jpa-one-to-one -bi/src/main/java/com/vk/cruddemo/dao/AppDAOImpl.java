package com.vk.cruddemo.dao;

import com.vk.cruddemo.entity.Instructor;
import com.vk.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
