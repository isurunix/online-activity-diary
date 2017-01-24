package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.DepartmentControllerInterface;
import lk.grp.synergy.model.Department;

import java.util.List;

/**
 * Created by USER on 1/24/2017.
 */
public class DepartmentController implements DepartmentControllerInterface {

    @Override
    public List<Department> getAllDepartments() {
        return null;
    }

    @Override
    public boolean updateDepartment(Department department) {
        return false;
    }

    @Override
    public boolean removeDepartment(Department department) {
        return false;
    }
}
