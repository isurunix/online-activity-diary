package lk.grp.synergy.control;

import lk.grp.synergy.model.Admin;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by isuru on 2/12/17.
 */
public interface AdminControllerInterface {
    boolean isValidLogin(Admin admin) throws SQLException, NamingException;

    Admin getAdmin(String username) throws SQLException, NamingException;
}
