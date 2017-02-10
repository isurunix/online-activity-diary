package lk.grp.synergy.control;

import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 1/12/17.
 */
public interface StudentControllerInterface {

    public ArrayList<Student> getAllStudents() throws SQLException, NamingException;
    public Student getStudent(String id) throws SQLException, NamingException;
    public Student updateStudent(Student student) throws SQLException;

    public List<Course> getCourseList(String studentId) throws SQLException, NamingException;

    List<Activity> getActivities(int studentId) throws SQLException, NamingException;
}
