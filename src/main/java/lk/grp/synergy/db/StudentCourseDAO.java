package lk.grp.synergy.db;

import lk.grp.synergy.model.StudentCourse;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 1/30/17.
 */
public class StudentCourseDAO {

    public List<String> getCourseList(int studentId) throws SQLException, NamingException {
        String sql = "SELECT course_code FROM student_course WHERE student_id=?";
        ArrayList<String> courseCodes = new ArrayList<>();

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ) {
            pstm.setInt(1,studentId);
            ResultSet resultSet = pstm.executeQuery();
            while(resultSet!=null && resultSet.next()){
                String courseCode = resultSet.getString("course_code");
                courseCodes.add(courseCode);
            }
        }

        return courseCodes;
    }

    public boolean addCourse(StudentCourse studentCourse) throws SQLException, NamingException {
        String sql = "INSERT INTO student_course (student_id, course_code, `group`) VALUES (?,?,?);";


        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setInt(1, Math.toIntExact(studentCourse.getStudent_id()));
            pstm.setString(2,studentCourse.getCourse_code());
            pstm.setString(3,studentCourse.getGroup());

            return pstm.executeUpdate() == 1;
        }
    }
}
