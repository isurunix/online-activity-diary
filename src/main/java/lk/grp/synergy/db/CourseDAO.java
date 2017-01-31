package lk.grp.synergy.db;

import lk.grp.synergy.model.Course;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by USER on 1/13/2017.
 */
public class CourseDAO {

    public CourseDAO() {

    }

    public ArrayList<Course> getAllCourse() throws SQLException, NamingException {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";

        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ) {
            ResultSet resultSet = prStmt.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    String code = resultSet.getString("course_code");
                    String name = resultSet.getString("name");
                    String courseCoodi = resultSet.getString("course_coodinator");
                    String acedamicCoodi = resultSet.getString("acedamic_coodinator");
                    int deptId = resultSet.getInt("dept_id");

                    courses.add(new Course(code, name, courseCoodi, acedamicCoodi, deptId));
                }
            }
        }

        return courses;
    }

    public Course getCourseBycode(String code) throws SQLException, NamingException {
        String sql = "SELECT * FROM course WHERE course_code=?";
        Course course = null;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setString(1,code);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet!=null){
                while (resultSet.next()){
                    String name = resultSet.getString("name");
                    String courseCoodi = resultSet.getString("course_coordinator");
                    String acedamicCoodi = resultSet.getString("academic_coordinator");
                    int deptId = resultSet.getInt("dept_id");

                    course = new Course(code, name, acedamicCoodi, deptId);
                }
            }
        }

        return course;
    }

    public boolean deleteCode(String Code) throws SQLException, NamingException {
        String sql = "DELETE FROM course WHERE course_code=?";
        boolean deleted = false;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setString(1,Code);
            deleted = pstm.executeUpdate() == 1;
        }
        return deleted;
    }

}

