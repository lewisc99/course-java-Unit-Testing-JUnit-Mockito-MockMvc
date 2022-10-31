package com.luv2code.springmvc.Service;


import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDAO;
    public void createStudent(String firstName, String lastName, String emailAddress)
    {
        CollegeStudent student = new CollegeStudent(firstName, lastName,emailAddress);
        student.setId(0);
        studentDAO.save(student);

    }

}
