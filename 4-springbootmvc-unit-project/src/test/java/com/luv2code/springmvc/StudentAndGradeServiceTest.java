package com.luv2code.springmvc;


import com.luv2code.springmvc.Service.StudentAndGradeService;
import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;


@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {


    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDAO;


    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    public void setupDatabase()
    {
        jdbc.execute("insert into student(id, firstname, lastname, email_address) " +
                "values (1, 'Eric','Roby', 'eric.roby@luv2code_school.com')");

    }

    @Test
    public void createStudentService()
    {
        studentService.createStudent("Chad","Darby","chad.darby@luv2code_school.com");

        CollegeStudent student = studentDAO.
                findByEmailAddress("chad.darby@luv2code_school.com");

        assertEquals("chad.darby@luv2code_school.com", student.getEmailAddress(),"find by Emaill");
    }

    @Test
    public void isStudentNullCheck()
    {
        assertTrue(studentService.checkIfStudentIsNull(1));
        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @AfterEach
    public void setAfterTransaction()
    {
        jdbc.execute("Delete from student");
    }

}
