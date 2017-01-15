package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.db.StudentDAO;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by isuru on 1/14/17.
 */
public class StudentControllerImpl implements StudentControllerInterface {
    private StudentDAO studentDAO;

    public StudentControllerImpl(){
        studentDAO = new StudentDAO();
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException {
        return null;
    }

    @Override
    public Student getStudent(String id) throws SQLException, NamingException {
        Student student = null;
        student = studentDAO.getStudentById(Integer.parseInt(id));
        return student;
    }

    @Override
    public Student updateStudent(Student student) throws SQLException {
        return null;
    }

}
