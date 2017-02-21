package lk.grp.synergy.db;

import lk.grp.synergy.model.Activity;
import lk.grp.synergy.util.ActivityType;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Activity> getAllActivities(String courseCode) throws SQLException, NamingException {
        ArrayList<Activity> activities = new ArrayList<>();
        String sql = "SELECT * FROM activity WHERE course_code=?";

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ){
            prStmt.setString(1,courseCode);
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
                    String code = courseCode;

                    activities.add(new Activity(id,ActivityType.valueOf(type),name,date,startTime,endTime,venue,group,code));
                }
            }
        }

        return activities;
    }

    public ArrayList<Activity> getAllActivities(String courseCode, LocalDate from, LocalDate to) throws SQLException, NamingException {
        ArrayList<Activity> activities = new ArrayList<>();
        String sql = "SELECT * FROM activity WHERE course_code=? AND date BETWEEN ? AND ?";

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ){
            prStmt.setString(1,courseCode);
            prStmt.setDate(2,Date.valueOf(from));
            prStmt.setDate(3,Date.valueOf(to));
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
                    String code = courseCode;

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


    public List<Integer> getCollisions(Activity activity) throws SQLException, NamingException {
        String sql_1 = "SELECT activity_id FROM activity WHERE date=? AND ((start_time>=? AND start_time<=?) OR " +
                "(start_time<=? AND ((end_time>? AND end_time<=?) OR end_time>?)))";

        ArrayList<Integer> activityIds = new ArrayList<>();

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql_1)
        ){
            pstm.setDate(1,Date.valueOf(activity.getDate()));
            pstm.setTime(2,Time.valueOf(activity.getStartTime()));
            pstm.setTime(3,Time.valueOf(activity.getEndTime()));
            pstm.setTime(4,Time.valueOf(activity.getStartTime()));
            pstm.setTime(5,Time.valueOf(activity.getStartTime()));
            pstm.setTime(6,Time.valueOf(activity.getEndTime()));
            pstm.setTime(7,Time.valueOf(activity.getEndTime()));

            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()){
                activityIds.add(resultSet.getInt("activity_id"));
            }
        }

        return activityIds;

    }

    public boolean addActivity(Activity activity) throws SQLException, NamingException {
        String sql = "INSERT INTO activity (name, date, start_time, end_time, venue, `group`, course_code, type) VALUES (?,?,?,?,?,?,?,?)";
        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setString(1,activity.getName());
            pstm.setDate(2,Date.valueOf(activity.getDate()));
            pstm.setTime(3, Time.valueOf(activity.getStartTime()));
            pstm.setTime(4, Time.valueOf(activity.getEndTime()));
            pstm.setString(5,activity.getVenue());
            pstm.setString(6,activity.getGroup());
            pstm.setString(7,activity.getCourseCode());
            pstm.setString(8,activity.getActivityType().name());

            return pstm.executeUpdate() == 1;
        }
    }

    public boolean updateActivity(Activity activity) throws SQLException, NamingException {
        String sql =
                "UPDATE activity " +
                "SET name=?, date=?, start_time=?, end_time=?, venue=?, `group`=? " +
                "WHERE activity_id=?";
        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setString(1, activity.getName());
            pstm.setDate(2,Date.valueOf(activity.getDate()));
            pstm.setTime(3,Time.valueOf(activity.getStartTime()));
            pstm.setTime(4, Time.valueOf(activity.getEndTime()));
            pstm.setString(5,activity.getVenue());
            pstm.setString(6, activity.getGroup());
            pstm.setInt(7, activity.getActivityId());

            return pstm.executeUpdate() == 1;
        }
    }

}


