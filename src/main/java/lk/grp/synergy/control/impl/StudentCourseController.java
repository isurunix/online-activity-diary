package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.CourseControllerInterface;
import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.control.StudentCourseControllerInterface;
import lk.grp.synergy.db.DBConnector;
import lk.grp.synergy.db.StudentCourseDAO;
import lk.grp.synergy.model.Course;
import lk.grp.synergy.model.Student;
import lk.grp.synergy.model.StudentCourse;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by isuru on 2/14/17.
 */
public class StudentCourseController implements StudentCourseControllerInterface {
    private StudentCourseDAO studentCourseDAO;
    private StudentControllerInterface studentController;
    private CourseControllerInterface courseController;

    public StudentCourseController() {
        studentCourseDAO = new StudentCourseDAO();
        studentController = new StudentControllerImpl();
        courseController = new CourseController();
    }

    @Override
    public boolean addCourse(int studentId, String courseCode, String group) throws SQLException, NamingException {
        Student student = studentController.getStudent(Integer.toString(studentId));
        Course course = courseController.getCourse(courseCode);

        if(student!=null && course!=null){
            return studentCourseDAO.addCourse(new StudentCourse(studentId,courseCode,group));
        }

        return false;
    }

    @Override
    public boolean removeCourse(String studentId, String courseCode) throws SQLException, NamingException {
        Student student = studentController.getStudent(studentId);
        if(student != null) {
            return studentCourseDAO.removeCourse(student.getStudentId(), courseCode);
        }
        return false;
    }
}
