package lk.grp.synergy.control;

import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public List<Activity> getAllActivities(Course course) throws SQLException, NamingException;

    /**
     * Get list of activities for a course within a given period of time
     * @param course the course
     * @param from period start date
     * @param to period end date
     * @return List of activities or null on error
     */
    public List<Activity> getAllActivitiesWithinPeriod(Course course, LocalDate from, LocalDate to) throws SQLException, NamingException;

    List<Activity> getAllActivities() throws SQLException, NamingException;

    /**
     * Update an existing activity
     * @param activity modified activity
     * @return true if update successful, false otherwise
     */
    public boolean updateActivity(Activity activity) throws SQLException;

    /**
     * Add a new activity
     * @param activity new activity data
     * @return true if added false otherwise
     */
    public boolean addActivity(Activity activity) throws SQLException, NamingException;

    /**
     * Remove an existing activity
     * @param activity activity to be removed
     * @return true if removed
     */
    public boolean removeActivity(Activity activity) throws SQLException;

    List<Integer> getCollisions(Activity activity) throws SQLException, NamingException;
}
