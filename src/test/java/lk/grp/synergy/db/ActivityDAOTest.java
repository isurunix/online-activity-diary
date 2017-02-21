package lk.grp.synergy.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.util.ActivityType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Created by isuru on 2/13/17.
 */
public class ActivityDAOTest {
    private static ActivityDAO activityDAO;

    @BeforeClass
    public static void setupClass(){
//        System.setProperty("ENV","TEST");
        activityDAO = new ActivityDAO();
    }

    @Test
    public void getCollisions() throws Exception {
        Activity activity = new Activity(0, ActivityType.DAY_SCHOOL,"Test Activity", LocalDate.of(2017,1,10),
                LocalTime.of(15,0,0),LocalTime.of(17,30,0),"-","-");
        assertEquals(2,(activityDAO.getCollisions(activity)).get(0).intValue());

        activity = new Activity(0, ActivityType.DAY_SCHOOL,"Test Activity", LocalDate.of(2017,6,1),
                LocalTime.of(9,0,0),LocalTime.of(16,0,0),"-","-");
        assertEquals(7,(activityDAO.getCollisions(activity)).get(0).intValue());
        assertEquals(8,(activityDAO.getCollisions(activity)).get(1).intValue());
    }

}