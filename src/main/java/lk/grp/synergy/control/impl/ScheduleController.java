package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.ScheduleControllerInterface;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by USER on 1/12/2017.
 */
public class ScheduleController implements ScheduleControllerInterface
{
    @Override
    public boolean addActivity(Student student, Activity activity) throws SQLException {
        return false;
    }

    @Override
    public boolean removeActivity(Student student, Activity activity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Activity> getActivityScheduleForStudent(Student student) throws SQLException {
        return null;
    }
}
