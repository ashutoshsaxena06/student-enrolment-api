package com.api.enrolment.student.controller;

import com.api.enrolment.student.appService.EnrolmentService;
import com.api.enrolment.student.entity.Student;
import com.api.enrolment.student.exceptions.EnrolmentException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrolmentController {
    private static final Logger log = Logger.getLogger(EnrolmentController.class);

    @Autowired
    private EnrolmentService enrolmentService;

    /**
     * @param student
     */
    @PostMapping("/newStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewStudent(@RequestBody Student student) {
        try {
            enrolmentService.createStudentById(student);
        } catch (EnrolmentException ex) {
            ex.printStackTrace();
            log.error("updateStudent : " + ex.getErrorMessage());
            processValidationError(ex);
        }
    }

    /**
     * @param student
     * @return
     */
    @PutMapping("/updateStudent")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Object updateStudentById(Student student) {
        try {
            return enrolmentService.updateStudentById(student);
        } catch (EnrolmentException ex) {
            ex.printStackTrace();
            log.error("updateStudent : " + ex.getErrorMessage());
            return processValidationError(ex);
        }
    }

    /**
     * @param student
     */
    @DeleteMapping("/removeStudent")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(Student student) {
        try {
            enrolmentService.deleteStudentById(student);
        } catch (EnrolmentException ex) {
            ex.printStackTrace();
            log.error("removeStudent : " + ex.getErrorMessage());
            processValidationError(ex);
        }
    }

    /**
     * @param className
     * @return
     */
    @GetMapping("/fetchStudents")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Student> getAllStudents(@Param("class") String className) {
        return enrolmentService.getAllStudents(className);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/fetchStudents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Student> getStudentById(Long id) {
        return enrolmentService.getStudentById(id);
    }

//    @GetMapping("/healthCheck")
////    @ResponseStatus(HttpStatus.OK)
//    public @ResponseBody
//    String healthCheck() {
//        return "Student Enrolment API is up and running !";
//    }

    /**
     * @param ex
     * @return
     */
    @ExceptionHandler(EnrolmentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    Object processValidationError(EnrolmentException ex) {
        String result = ex.getErrorMessage();
        log.info("###########" + result);
        return ex;
    }
}
