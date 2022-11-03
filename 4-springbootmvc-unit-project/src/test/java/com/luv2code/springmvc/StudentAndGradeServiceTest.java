package com.luv2code.springmvc;


import com.luv2code.springmvc.Service.StudentAndGradeService;
import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradesDao;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.ScienceGradeDao;
import com.luv2code.springmvc.repository.StudentDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
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


    @Autowired
    private MathGradesDao mathGradesDao;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    private HistoryGradesDao historyGradesDao;

    @Value("${sql.script.create.math.grade}")
    private String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    private  String sqlAddMathStudent;

    @Value("${sql.script.create.science.grade}")
    private  String sqlAddScienceStudent;

    @Value("${sql.script.create.history.grade}")
    private  String sqlAddHistoryStudent;

    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach
    public void setupDatabase()
    {
        jdbc.execute("insert into student(id, firstname, lastname, email_address) " +
                "values (1, 'Eric','Roby', 'eric.roby@luv2code_school.com')");


        jdbc.execute("insert into math_grade(id,student_id,grade) values (1,1,100.00)");
        jdbc.execute("insert into science_grade(id,student_id,grade) values (1,1,100.00)");
        jdbc.execute("insert into history_grade(id,student_id,grade) values (1,1,100.00)");
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
        Optional<MathGrade> deletedMathGrade = mathGradesDao.findById(1);
        Optional<HistoryGrade> deletedHistoryGrade = historyGradesDao.findById(1);
        Optional<ScienceGrade> deletedScienceGrade = scienceGradeDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "return True");
        assertTrue(deletedMathGrade.isPresent());
        assertTrue(deletedScienceGrade.isPresent());
        assertTrue(deletedHistoryGrade.isPresent());

        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDAO.findById(1);
        deletedMathGrade = mathGradesDao.findById(1);
        deletedScienceGrade = scienceGradeDao.findById(1);
        deletedHistoryGrade = historyGradesDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(),"Return false");
        assertFalse(deletedMathGrade.isPresent());
        assertFalse(deletedScienceGrade.isPresent());
        assertFalse(deletedHistoryGrade.isPresent());

    }


    @Sql("/insertData.sql")
    @Test
    public void getGradeBookService()
    {
        Iterable<CollegeStudent> iterableCollegeStudent = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for (CollegeStudent collegeStudent : iterableCollegeStudent)
        {
            collegeStudents.add(collegeStudent);
        }

    assertEquals(5, collegeStudents.size());
    }


    @Test
    public void createGradeService()
    {
        //Create the grade
        assertTrue(studentService.createGrade(80.50,1,"math"));
        assertTrue(studentService.createGrade(80.50,1,"science"));
        assertTrue(studentService.createGrade(80.50,1,"history"));

        //Get All grades with StudentId
        Iterable<MathGrade> mathGrades = mathGradesDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradesDao.findGradeByStudentId(1);

        //verify the size of the grades
        assertTrue(((Collection<MathGrade>) mathGrades).size() == 2,"Student has math Grades");
        assertTrue(((Collection<ScienceGrade>) scienceGrades).size() == 2);
        assertTrue(((Collection<HistoryGrade>) historyGrades).size() == 2);
    }

    @Test
    public void createGradeServiceReturnFalse()
    {
        assertFalse(studentService.createGrade(105,1, "math"));
        assertFalse(studentService.createGrade(-5,1,"math"));
        //invalid id because we only have id one in beforeEach
        assertFalse(studentService.createGrade(80.50,2,"math"));
        assertFalse(studentService.createGrade(80.50,1,"Literature"));
    }


    @Test
    public void deleteGradeService()
    {
        assertEquals(1, studentService.deleteGrade(1,"math"), "Returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1,"science"), "Returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1,"history"), "Returns student id after delete");
    }


    @Test
    public void deleteGradeServiceReturnStudentIdOfZero()
    {
        assertEquals(0, studentService.deleteGrade(0, "science"),
                "No student should have 0 id");

        assertEquals(0, studentService.deleteGrade(1, "literature"),
                "No student have a literature class");
    }

    @Test
    public void studentInformation()
    {
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(1);

        assertNotNull(gradebookCollegeStudent);
        assertEquals(1, gradebookCollegeStudent.getId());
        assertEquals("Eric", gradebookCollegeStudent.getFirstname());
        assertEquals("Roby", gradebookCollegeStudent.getLastname());
        assertEquals("eric.roby@luv2code_school.com", gradebookCollegeStudent.getEmailAddress());
        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size() == 1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size() == 1);
    }

    @Test
    public void studentInformationServiceReturnNull()
    {
        GradebookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(0);

        assertNull(gradebookCollegeStudent);
    }
    @AfterEach
    public void setAfterTransaction()
    {
        jdbc.execute("Delete from student");
        jdbc.execute("Delete from math_grade");
        jdbc.execute("Delete from science_grade");
        jdbc.execute("Delete from history_grade");
    }

}
