package lk.grp.synergy.control;

import lk.grp.synergy.model.Course;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by isuru on 1/19/17.
 */
public interface CourseControllerInterface {

    /**
     * Get course details for a given course code
     * @param courseCode the code of the course to retrieve
     * @return instance of Course with data if code is correct, null otherwise
     */
    public Course getCourse(String courseCode) throws SQLException, NamingException;

    /**
     * remove an existing course
     * @param courseCode course code of the course to remove
     * @return true if removed, false otherwise
     */
    public boolean removeCourse(String courseCode) throws SQLException, NamingException;

    /**
     * Update course details
     * @param course Course with updated details
     * @return true if updated, false otherwise
     */
    public boolean updateCourse(Course course) throws SQLException;

    /**
     * Get all exiting course codes
     * @return List of existing course codes
     */
    List<String> getAllCourses() throws SQLException, NamingException;
}
