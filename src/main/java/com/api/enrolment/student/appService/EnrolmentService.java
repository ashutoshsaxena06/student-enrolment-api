package com.api.enrolment.student.appService;

import com.api.enrolment.student.entity.Student;
import com.api.enrolment.student.exceptions.EnrolmentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrolmentService {

    void createStudentById(Student student) throws EnrolmentException;

    Object updateStudentById(Student student) throws EnrolmentException;

    void deleteStudentById(Student student) throws EnrolmentException;

    List<Student> getAllStudents(String className);

    List<Student> getStudentById(Long id);
}
