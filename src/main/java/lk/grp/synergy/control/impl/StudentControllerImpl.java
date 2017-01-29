package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.db.CourseDAO;
import lk.grp.synergy.db.StudentCourseDAO;
import lk.grp.synergy.db.StudentDAO;
import lk.grp.synergy.model.Course;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 1/14/17.
 */
public class StudentControllerImpl implements StudentControllerInterface {
    private StudentDAO studentDAO;
    private StudentCourseDAO studentCourseDAO;
    private CourseDAO courseDAO;

    public StudentControllerImpl(){
        studentDAO = new StudentDAO();
        studentCourseDAO = new StudentCourseDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException, NamingException {
        ArrayList<Student> allStudents = studentDAO.getAllStudents();
        return allStudents;
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

    @Override
    public List<Course> getCourseList(String studentId) throws SQLException, NamingException {
        List<String> courseList = studentCourseDAO.getCourseList(Integer.parseInt(studentId));
        ArrayList<Course> courses = new ArrayList<>();
        for(String courseCode : courseList){
            Course course = courseDAO.getCourseBycode(courseCode);
            courses.add(course);
        }
        return courses;
    }

}
