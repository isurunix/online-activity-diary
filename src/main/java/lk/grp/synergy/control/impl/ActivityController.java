package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.ActivityControllerInterface;
import lk.grp.synergy.db.ActivityDAO;
import lk.grp.synergy.db.NotificationReqDAO;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 1/20/17.
 */
public class ActivityController implements ActivityControllerInterface {
    private ActivityDAO activityDAO;
    private NotificationReqDAO notificationReqDAO;

    public ActivityController(){
        activityDAO = new ActivityDAO();
        notificationReqDAO = new NotificationReqDAO();
    }

    @Override
    public List<Activity> getAllActivities(Course course) throws SQLException, NamingException {
        ArrayList<Activity> activities = activityDAO.getAllActivities(course.getCourseCode());
        return activities;
    }

    @Override
    public List<Activity> getAllActivitiesWithinPeriod(Course course, LocalDate from, LocalDate to) throws SQLException, NamingException {
        ArrayList<Activity> activities = activityDAO.getAllActivities(course.getCourseCode(), from, to);
        return activities;
    }

    @Override
    public List<Activity> getAllActivities() throws SQLException, NamingException {
        return activityDAO.getAllActivities();
    }

    @Override
    public boolean updateActivity(Activity activity) throws SQLException {
        return false;
    }

    @Override
    public boolean addActivity(Activity activity) throws SQLException, NamingException {
        boolean added = activityDAO.addActivity(activity);
        if(added){
            String message = activity.getCourseCode()+": "+activity.getName()+"\n"+
                    "scheduled on "+activity.getDate().format(DateTimeFormatter.ISO_DATE)+"\n from " +
                    activity.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "to" +
                    activity.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            notificationReqDAO.addNotificationReq(message,activity.getCourseCode());
        }
        return added;
    }

    @Override
    public boolean removeActivity(Activity activity) throws SQLException {
        return false;
    }

    @Override
    public List<Integer> getCollisions(Activity activity) throws SQLException, NamingException {
        return activityDAO.getCollisions(activity);
    }
}
