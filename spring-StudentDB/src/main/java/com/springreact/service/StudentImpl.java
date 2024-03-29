package com.springreact.service;

import com.springreact.exception.StudentAlreadyExistsException;
import com.springreact.exception.StudentNotFoundException;
import com.springreact.model.Student;
import com.springreact.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentImpl implements IStudentService {

    // try to add final
    private StudentRepository theStudentRepository;

    @Override
    public List<Student> getStudents() {
        return theStudentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())) {
            throw new StudentAlreadyExistsException(student.getEmail() + "Student Already Exists!");
        }

        return theStudentRepository.save(student);
    }

    private boolean studentAlreadyExists(String email) {
        return theStudentRepository.findByEmail(email)
                                   .isPresent();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return theStudentRepository.findById(id)
                                   .map(st -> {
                                       st.setFirstName(student.getFirstName());
                                       st.setLastName(student.getLastName());
                                       st.setEmail(student.getEmail());
                                       st.setDepartment(student.getDepartment());

                                       return theStudentRepository.save(st);
                                   })
                                   .orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found"));
    }

    @Override
    public Student getStudentById(Long id) {
        return theStudentRepository.findById(id)
                                   .orElseThrow(() -> new StudentNotFoundException("Sorry, student with the id: " + id + " could not be found"));
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!theStudentRepository.existsById(id)) {
            throw new StudentNotFoundException("Sorry, this student could not be found");
        }

        theStudentRepository.deleteById(id);
    }
}
