package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.StudentCourseControllerInterface;
import lk.grp.synergy.db.DBConnector;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by isuru on 2/14/17.
 */
public class StudentCourseController implements StudentCourseControllerInterface {

    @Override
    public boolean addCourse(int studentId, String courseCode, String group) throws SQLException, NamingException {
        boolean added = false;
        String sql = "INSERT INTO student_course (`student_id`,`course_code`,`group`) VALUES (?,?,?)";

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setInt(1, studentId);
            pstm.setString(2, courseCode);
            pstm.setString(3,group);

            added = pstm.executeUpdate() == 1;
        }

        return added;
    }
}
