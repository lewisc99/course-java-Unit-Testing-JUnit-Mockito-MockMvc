package com.luv2code.springmvc.Service;


import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.HistoryGrade;
import com.luv2code.springmvc.models.MathGrade;
import com.luv2code.springmvc.models.ScienceGrade;
import com.luv2code.springmvc.repository.HistoryGradesDao;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.ScienceGradeDao;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDAO;

    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;

    @Autowired
    private MathGradesDao mathGradesDao;

    @Autowired
    @Qualifier("scienceGrades")
    private ScienceGrade scienceGrade;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    @Qualifier("historyGrades")
    private HistoryGrade historyGrade;

    @Autowired
    private HistoryGradesDao historyGradesDao;


    public void createStudent(String firstName, String lastName, String emailAddress)
    {
        CollegeStudent student = new CollegeStudent(firstName, lastName,emailAddress);
        student.setId(0);
        studentDAO.save(student);

    }

    public boolean checkIfStudentIsNull(int id)
    {
        Optional<CollegeStudent> student = studentDAO.findById(id);

        if (student.isPresent())
        {
            return true;
        }
        return false;
    }

    public void deleteStudent(int id)
    {
        if (checkIfStudentIsNull(id))
        {
            studentDAO.deleteById(id);
        }
    }

    public Iterable<CollegeStudent> getGradebook()
    {
        Iterable<CollegeStudent> collegeStudents = studentDAO.findAll();
        return collegeStudents;
    }

    public boolean createGrade(double grade, int studentId, String gradeType)
    {
        if (!checkIfStudentIsNull(studentId))
        {
            return false;
        }
        if (grade >= 0 && grade <= 100)
        {
            if (gradeType.equals("math"))
            {
                mathGrade.setId(0);
                mathGrade.setGrade(grade);
                mathGrade.setStudentId(studentId);
                mathGradesDao.save(mathGrade);

                return true;
            }
            if (gradeType.equals("science"))
            {
                scienceGrade.setId(0);
                scienceGrade.setGrade(grade);
                scienceGrade.setStudentId(studentId);
                scienceGradeDao.save(scienceGrade);

                return true;
            }
            if (gradeType.equals("history"))
            {
                historyGrade.setId(0);
                historyGrade.setGrade(grade);
                historyGrade.setStudentId(studentId);
                historyGradesDao.save(historyGrade);

                return true;
            }
        }
        return true;
    }

}
