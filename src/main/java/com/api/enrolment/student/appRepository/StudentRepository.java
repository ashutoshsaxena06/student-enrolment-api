package com.api.enrolment.student.appRepository;

import com.api.enrolment.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);

}
