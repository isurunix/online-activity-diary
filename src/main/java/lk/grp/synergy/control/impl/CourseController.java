package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.CourseControllerInterface;
import lk.grp.synergy.db.CourseDAO;
import lk.grp.synergy.model.Course;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by USER on 1/24/2017.
 */
public class CourseController implements CourseControllerInterface {
    private CourseDAO courseDAO;

    public CourseController(){
        courseDAO = new CourseDAO();
    }

    @Override
    public Course getCourse(String courseCode) throws SQLException, NamingException {
        Course courseBycode = courseDAO.getCourseBycode(courseCode);
        return courseBycode;
    }

    @Override
    public boolean removeCourse(String courseCode) throws SQLException, NamingException {
        boolean removed = courseDAO.deleteCode(courseCode);
        return removed;
    }

    @Override
    public boolean updateCourse(Course course) throws SQLException {
        return false;
    }
}
