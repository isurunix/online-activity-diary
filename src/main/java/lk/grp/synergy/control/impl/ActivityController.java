package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.ActivityControllerInterface;
import lk.grp.synergy.db.ActivityDAO;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuru on 1/20/17.
 */
public class ActivityController implements ActivityControllerInterface {
    private ActivityDAO activityDAO;

    public ActivityController(){
        activityDAO = new ActivityDAO();
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
    public boolean updateActivity(Activity activity) throws SQLException {
        return false;
    }

    @Override
    public boolean addActivity(Activity activity)throws SQLException {
        return false;
    }

    @Override
    public boolean removeActivity(Activity activity) throws SQLException {
        return false;
    }
}
