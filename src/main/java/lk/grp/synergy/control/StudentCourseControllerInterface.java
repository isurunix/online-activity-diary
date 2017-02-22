package lk.grp.synergy.control;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by isuru on 2/14/17.
 */
public interface StudentCourseControllerInterface {

    boolean addCourse(int studentId, String courseCode, String group) throws SQLException, NamingException;

    boolean removeCourse(String studentId, String courseCode) throws SQLException, NamingException;
}
