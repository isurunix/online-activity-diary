package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.CourseControllerInterface;
import lk.grp.synergy.db.CourseDAO;
import lk.grp.synergy.model.Course;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getAllCourses() throws SQLException, NamingException {
        ArrayList<Course> courses = courseDAO.getAllCourse();
        ArrayList<String> courseCodes = new ArrayList<>();
        courses.forEach(course -> {
            courseCodes.add(course.getCourseCode());
        });
        return courseCodes;
    }
}
