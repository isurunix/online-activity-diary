package lk.grp.synergy.db;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by isuru on 2/14/17.
 */
public class NotificationReqDAO {

    public boolean addNotificationReq(String message, String courseCode) throws SQLException, NamingException {
        String sql = "INSERT INTO notification_req (course_code, msg) VALUES (?,?)";
        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setString(1,courseCode);
            pstm.setString(2,message);

            return pstm.executeUpdate() == 1;
        }
    }
}
