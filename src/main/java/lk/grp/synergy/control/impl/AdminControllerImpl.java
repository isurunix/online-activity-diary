package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.AdminControllerInterface;
import lk.grp.synergy.db.AdminDAO;
import lk.grp.synergy.model.Admin;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by isuru on 2/12/17.
 */
public class AdminControllerImpl implements AdminControllerInterface {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    public boolean isValidLogin(Admin admin) throws SQLException, NamingException {
        Admin adminByUsername = adminDAO.getAdminByUsername(admin.getUsername());
        if(adminByUsername!=null) {
            return admin.getPassword().equals(adminByUsername.getPassword());
        }
        return false;
    }

    @Override
    public Admin getAdmin(String username) throws SQLException, NamingException {
        return adminDAO.getAdminByUsername(username);
    }
}
