package lk.grp.synergy.db;

import lk.grp.synergy.model.Activity;
import lk.grp.synergy.util.ActivityType;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by USER on 1/13/2017.
 */
public class ActivityDAO {

    public ActivityDAO(){

    }

    public ArrayList<Activity> getAllActivities() throws SQLException, NamingException {
        ArrayList<Activity> activities = new ArrayList<>();
        String sql = "SELECT * FROM activity";

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ){
            ResultSet resultSet = prStmt.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    int id = resultSet.getInt("activity_id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
                    LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
                    String venue = resultSet.getString("venue");
                    String group = resultSet.getString("group");
                    String code = resultSet.getString("course_code");

                    activities.add(new Activity(id,ActivityType.valueOf(type),name,date,startTime,endTime,venue,group,code));
                }
            }
        }

        return activities;
    }

    /**
     * Retrieve activity with given id
     * @param activityId the id of the student to retrieve
     * @return Activity if record exists null otherwise
     */
    public Activity getActivityById(int activityId) throws SQLException, NamingException {
        String sql = "SELECT * FROM activity WHERE activity_id=?";
        Activity activity = null;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setInt(1,activityId);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet!=null){
                while (resultSet.next()){
                    int id = resultSet.getInt("activity_id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
                    LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
                    String venue = resultSet.getString("venue");
                    String group = resultSet.getString("group");
                    String code = resultSet.getString("course_code");


                    activity = new Activity(id,ActivityType.valueOf(type),name,date,startTime,endTime,venue,group,code);
                }
            }
        }

        return activity;
    }

    /**
     * Delete activity with given id from the database
     * @param activityId the id of the activity to delete
     * @return true if removed false otherwise
     */
    public boolean deleteActivity(int activityId) throws SQLException, NamingException {
        String sql = "DELETE FROM activity WHERE activity_id=?";
        boolean deleted = false;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setInt(1,activityId);
            deleted = pstm.executeUpdate() == 1;
        }
        return deleted;
    }

}
