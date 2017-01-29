package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.DepartmentControllerInterface;
import lk.grp.synergy.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by USER on 1/24/2017.
 */
public class DepartmentController implements DepartmentControllerInterface {

    @Override
    public List<Department> getAllDepartments() throws SQLException {
        return null;
    }

    @Override
    public boolean updateDepartment(Department department) throws SQLException {
        return false;
    }

    @Override
    public boolean removeDepartment(Department department) throws SQLException {
        return false;
    }
}
