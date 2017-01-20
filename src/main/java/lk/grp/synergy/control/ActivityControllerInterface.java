package lk.grp.synergy.control;

import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by isuru on 1/19/17.
 */
public interface ActivityControllerInterface {

    /**
     * Get list of all activities for a given course
     * @param course the course to fetch the activies
     * @return List of activities or null on error
     */
    public List<Activity> getAllActivities(Course course);

    /**
     * Get list of activities for a course within a given period of time
     * @param course the course
     * @param from period start date/time
     * @param to period end date/time
     * @return List of activities or null on error
     */
    public List<Activity> getAllActivitiesWithinPeriod(Course course, LocalDateTime from, LocalDateTime to);

    /**
     * Update an existing activity
     * @param activity modified activity
     * @return true if update successful, false otherwise
     */
    public boolean updateActivity(Activity activity);

    /**
     * Add a new activity
     * @param activity new activity data
     * @return true if added false otherwise
     */
    public boolean addActivity(Activity activity);

    /**
     * Remove an existing activity
     * @param activity activity to be removed
     * @return true if removed
     */
    public boolean removeActivity(Activity activity);
}
