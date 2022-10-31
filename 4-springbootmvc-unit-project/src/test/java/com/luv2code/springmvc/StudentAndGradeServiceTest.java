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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @Test
    public void deleteStudentService()
    {
        Optional<CollegeStudent> deletedCollegeStudent = studentDAO.findById(1);
        assertTrue(deletedCollegeStudent.isPresent(), "return True");

        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDAO.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(),"Return false");


    }

    @Test
    public void getGradeBookService()
    {
        Iterable<CollegeStudent> iterableCollegeStudent = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for (CollegeStudent collegeStudent : iterableCollegeStudent)
        {
            collegeStudents.add(collegeStudent);
        }

    assertEquals(1, collegeStudents.size());
    }


    @AfterEach
    public void setAfterTransaction()
    {
        jdbc.execute("Delete from student");
    }

}
