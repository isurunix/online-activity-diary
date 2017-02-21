package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.AdminCourseControllerInterface;
import lk.grp.synergy.db.AdminCourseDAO;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by isuru on 2/12/17.
 */
public class AdminCourseController implements AdminCourseControllerInterface {
    private AdminCourseDAO adminCourseDAO;

    public AdminCourseController() {
        this.adminCourseDAO = new AdminCourseDAO();
    }

    @Override
    public List<String> getCourseList(int adminId) throws SQLException, NamingException {
        return adminCourseDAO.getCourseList(adminId);
    }
}
