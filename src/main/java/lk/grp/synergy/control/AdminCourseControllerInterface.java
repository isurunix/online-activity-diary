package lk.grp.synergy.control;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by isuru on 2/12/17.
 */
public interface AdminCourseControllerInterface {
    List<String> getCourseList(int adminId) throws SQLException, NamingException;
}
