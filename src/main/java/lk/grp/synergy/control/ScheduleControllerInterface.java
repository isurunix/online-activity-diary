package lk.grp.synergy.control;

import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by isuru on 1/12/17.
 */
public interface ScheduleControllerInterface {

    public boolean addActivity(Student student, Activity activity) throws SQLException;
    public boolean removeActivity(Student student, Activity activity) throws SQLException;
    public ArrayList<Activity> getActivityScheduleForStudent(Student student) throws SQLException, NamingException;
}
