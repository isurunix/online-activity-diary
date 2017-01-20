package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.ActivityControllerInterface;
import lk.grp.synergy.db.ActivityDAO;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;

import java.time.LocalDateTime;
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
    public List<Activity> getAllActivities(Course course) {
        return null;
    }

    @Override
    public List<Activity> getAllActivitiesWithinPeriod(Course course, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public boolean updateActivity(Activity activity) {
        return false;
    }

    @Override
    public boolean addActivity(Activity activity) {
        return false;
    }

    @Override
    public boolean removeActivity(Activity activity) {
        return false;
    }
}
