package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.ScheduleControllerInterface;
import lk.grp.synergy.db.ActivityDAO;
import lk.grp.synergy.db.StudentCourseDAO;
import lk.grp.synergy.db.StudentDAO;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by USER on 1/12/2017.
 */
public class ScheduleController implements ScheduleControllerInterface {

    private ActivityDAO activityDAO;
    private StudentDAO studentDAO;
    private StudentCourseDAO studentCourseDAO;

    public ScheduleController() {
        activityDAO = new ActivityDAO();
        studentDAO = new StudentDAO();
        studentCourseDAO = new StudentCourseDAO();
    }

    @Override
    public boolean addActivity(Student student, Activity activity) throws SQLException {
        return false;
    }

    @Override
    public boolean removeActivity(Student student, Activity activity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Activity> getActivityScheduleForStudent(Student student) throws SQLException, NamingException {
        ArrayList<Activity> activities = new ArrayList<>();
        List<String> courseList = studentCourseDAO.getCourseList(student.getStudentId());
        for(String courseCode : courseList){
            ArrayList<Activity> allActivities = activityDAO.getAllActivities(courseCode);
            activities.addAll(allActivities);
        }

        Collections.sort(activities, (a1, a2) -> {
            LocalDate date1 = ((Activity) a1).getDate();
            LocalTime startTime1 = ((Activity) a1).getStartTime();
            LocalDateTime dateTime1 = date1.atTime(startTime1);

            LocalDate date2 = ((Activity) a2).getDate();
            LocalTime startTime2 = ((Activity) a2).getStartTime();
            LocalDateTime dateTime2 = date2.atTime(startTime2);

            return dateTime1.compareTo(dateTime2);
        });

        return activities;
    }
}
