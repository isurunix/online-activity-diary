package lk.grp.synergy.db;

import lk.grp.synergy.model.Activity;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by USER on 1/13/2017.
 */
public class ActivityDAO {

    public ActivityDAO(){

    }

    public ArrayList<Activity> getAllActivity() throws SQLException, NamingException {
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
                    Date date = resultSet.getDate("date");
                    Time str_time = resultSet.getTime("start_time");
                    Time end_time = resultSet.getTime("end_time");
                    String venue = resultSet.getString("venue");
                    String group = resultSet.getString("group");
                    String code = resultSet.getString("course_code");

                    activities.add(new Activity(id,name,date,str_time,end_time,venue,group,code));
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
                    String name = resultSet.getString("name");
                    Date date = resultSet.getDate("date");
                    Time str_time = resultSet.getTime("start_time");
                    Time end_time = resultSet.getTime("end_time");
                    String venue = resultSet.getString("venue");
                    String group = resultSet.getString("group");
                    String code = resultSet.getString("course_code");

                    activity = new Activity(activityId,name,date,str_time,end_time,venue,group,code);
                }
            }
        }

        return activity;
    }
}
