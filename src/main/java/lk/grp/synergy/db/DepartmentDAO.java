package lk.grp.synergy.db;

import lk.grp.synergy.model.Department;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by isuru on 1/13/17.
 */
public class DepartmentDAO {

    public DepartmentDAO(){

    }

    public ArrayList<Department> getAllDepartments() throws SQLException, NamingException {
        ArrayList<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ){
            ResultSet resultSet = prStmt.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    int id = resultSet.getInt("dept_id");
                    String name = resultSet.getString("dept_name");
                    String email = resultSet.getString("dept_email");
                    int telephone = resultSet.getInt("dept_tele");
                    int facId = resultSet.getInt("fac_id");

                    departments.add(new Department(id,name,email,telephone,facId));
                }
            }
        }

        return departments;
    }

    /**
     * Retrieve department with given id
     * @param departmentId the id of the student to retrieve
     * @return Department if record exists null otherwise
     */
    public Department getDepartmentById(int departmentId) throws SQLException, NamingException {
        String sql = "SELECT * FROM department WHERE dept_id=?";
        Department department = null;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setInt(1,departmentId);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet!=null){
                while (resultSet.next()){
                    String name = resultSet.getString("dept_name");
                    String email = resultSet.getString("dept_email");
                    int telephone = resultSet.getInt("dept_tele");
                    int facId = resultSet.getInt("fac_id");

                    department = new Department(departmentId,name,email,telephone,facId);
                }
            }
        }

        return department;
    }

    /**
     * Delete department with given id from the database
     * @param departmentId the id of the department to delete
     * @return true if removed false otherwise
     */
    public boolean deleteDepartment(int departmentId) throws SQLException, NamingException {
        String sql = "DELETE FROM department WHERE dept_id=?";
        boolean deleted = false;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setInt(1,departmentId);
            deleted = pstm.executeUpdate() == 1;
        }
        return deleted;
    }

}
