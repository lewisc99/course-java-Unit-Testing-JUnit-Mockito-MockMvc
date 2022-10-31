package com.luv2code.springmvc;


import com.luv2code.springmvc.Service.StudentAndGradeService;
import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {


    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDAO;

    @Test
    public void createStudentService()
    {
        studentService.createStudent("Chad","Darby","chad.darby@luv2code_school.com");

        CollegeStudent student = studentDAO.
                findByEmailAddress("chad.darby@luv2code_school.com");

        assertEquals("chad.darby@luv2code_school.com", student.getEmailAddress(),"find by Emaill");
    }


}
