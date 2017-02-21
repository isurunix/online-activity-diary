package lk.grp.synergy.db;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 2/12/17.
 */
public class AdminCourseDAO {

    public AdminCourseDAO() {
    }

    public List<String> getCourseList(int adminId) throws SQLException, NamingException {
        String sql = "SELECT * FROM course_admin WHERE admin_id=?";
        ArrayList<String> courses = new ArrayList<>();

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement psmt = con.prepareStatement(sql)
        ){
            psmt.setInt(1,adminId);
            ResultSet resultSet = psmt.executeQuery();

            while(resultSet.next()){
                courses.add(resultSet.getString("course_code"));
            }
        }
        return courses;
    }
}
