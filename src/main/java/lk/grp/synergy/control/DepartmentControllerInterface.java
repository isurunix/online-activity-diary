package lk.grp.synergy.control;

import lk.grp.synergy.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by isuru on 1/19/17.
 */
public interface DepartmentControllerInterface {

    /**
     * Get list of all departments
     * @return List of all apartments
     */
    public List<Department> getAllDepartments() throws SQLException;

    /**
     * Update department details
     * @param department Department with updated details
     * @return true if updated false otherwise
     */
    public boolean updateDepartment(Department department) throws SQLException;

    /**
     * Remove department details
     * @param department Department to remove from database
     * @return true if removed false otherwise
     */
    public boolean removeDepartment(Department department) throws SQLException;
}
