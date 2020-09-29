package com.api.enrolment.student.appService;

import com.api.enrolment.student.App.ApplicationStudentEnrolment;
import com.api.enrolment.student.entity.Student;
import com.api.enrolment.student.exceptions.EnrolmentException;
import com.api.enrolment.student.appRepository.StudentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EnrolmentServiceImpl implements EnrolmentService {
    private static final Logger log = Logger.getLogger(ApplicationStudentEnrolment.class);

    @Autowired
    private StudentRepository studentRepository;

    /**
     * @param student
     * @throws EnrolmentException
     */
    @Override
    public void createStudentById(Student student) throws EnrolmentException {
        if (student.getId() == 0L || studentRepository.findById(student.getId()).isPresent()) {
            throw new EnrolmentException("Student ID already exist or Invalid", "ES003");
        }
        if (student.getFirstName() == null && student.getLastName() == null && student.getClassName() == null && student.getNationality() == null) {
            throw new EnrolmentException("Missing Student details", "ES002");
        }
        studentRepository.save(student);
        log.info("******new student added ********");
        log.info(student.toString());
        log.info("**************");
    }

    /**
     * @param student
     * @return
     * @throws EnrolmentException
     */
    @Override
    public Student updateStudentById(Student student) throws EnrolmentException {
        Student existingStudent = studentRepository.findById(student.getId()).orElse(null);
        if (existingStudent == null) {
            throw new EnrolmentException("Student not enrolled", "ES001");
        }
        existingStudent.setClassName(student.getClassName());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setNationality(student.getNationality());
        log.info("******updated student record ********");
        log.info(existingStudent.toString());
        log.info("**************");
        return studentRepository.save(existingStudent);
    }

    /**
     * @param student
     * @throws EnrolmentException
     */
    @Override
    public void deleteStudentById(Student student) throws EnrolmentException {
        Student existingStudent = student.getId() != 0L ? studentRepository.findById(student.getId()).orElse(null) : null;
        if (existingStudent == null) {
            throw new EnrolmentException("Student not enrolled", "ES001");
        }
        studentRepository.deleteById(existingStudent.getId());
        log.info("******deleted student record ********");
        log.info(existingStudent.toString());
        log.info("**************");
    }

    /**
     * @param className
     * @return
     */
    @Override
    public List<Student> getAllStudents(String className) {
        log.info("******fetch bulk records ********");
        log.info("Using class name : " + className);
        return studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getClassName().equals(className))
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Student> getStudentById(Long id) {
        log.info("******fetch student record ********");
        log.info("Using student ID : " + id);
        return studentRepository
                .findAllById(Collections.singleton(id));
    }
}
