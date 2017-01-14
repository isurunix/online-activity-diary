package lk.grp.synergy.db;

import lk.grp.synergy.model.Course;
import lk.grp.synergy.model.Notification;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Created by USER on 1/13/2017.
 */
public class NotificationDAO {

    public NotificationDAO(){

    }

    public ArrayList<Notification> getAllNotification() throws SQLException, NamingException {
        ArrayList<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notification";

        try (
                Connection con = DBConnector.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ) {
            ResultSet resultSet = prStmt.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("notification_id");
                    String msg = resultSet.getString("message");
                    int channel = resultSet.getInt("channel");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    LocalDateTime scheduledTime = resultSet.getTimestamp("scheduled_time").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDateTime deliveredTime = resultSet.getTimestamp("delivered_time").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    int stdId = resultSet.getInt("student_id");

                    notifications.add(new Notification(id,stdId,msg,channel,from,to,scheduledTime,deliveredTime));
                }
            }
        }

        return notifications;
    }

    public Notification getNotificationById(int notificationId) throws SQLException, NamingException {
        String sql = "SELECT * FROM notification WHERE notification_id=?";
        Notification notification = null;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setInt(1,notificationId);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet!=null){
                while (resultSet.next()){
                    String msg = resultSet.getString("message");
                    int channel = resultSet.getInt("channel");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    LocalDateTime scheduledTime = resultSet.getTimestamp("scheduled_time").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDateTime deliveredTime = resultSet.getTimestamp("delivered_time").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    int stdId = resultSet.getInt("student_id");

                    notification = new Notification(notificationId,stdId,msg,channel,from,to,scheduledTime,deliveredTime);
                }
            }
        }

        return notification;
    }

    /**
     * Delete notification with given id from the database
     * @param notificationId the id of the notification to delete
     * @return true if removed false otherwise
     */
    public boolean deleteNotification(int notificationId) throws SQLException, NamingException {
        String sql = "DELETE FROM notification WHERE notification_id=?";
        boolean deleted = false;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setInt(1,notificationId);
            deleted = pstm.executeUpdate() == 1;
        }
        return deleted;
    }

}

