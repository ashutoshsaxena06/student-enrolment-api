package com.api.enrolment.student.App;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@ComponentScan
@SpringBootApplication
public class ApplicationStudentEnrolment {
    private static final Logger log = Logger.getLogger(ApplicationStudentEnrolment.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStudentEnrolment.class, args);
        log.info("App started");
    }


}
