package lk.grp.synergy.db;

import lk.grp.synergy.model.Faculty;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by isuru on 1/13/17.
 */
public class FacultyDAO {

    public FacultyDAO(){

    }

    public ArrayList<Faculty> getAllStudents() throws SQLException, NamingException {
        ArrayList<Faculty> faculties = new ArrayList<>();
        String sql = "SELECT * FROM faculty";

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement prStmt = con.prepareStatement(sql)
        ){
            ResultSet resultSet = prStmt.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    int id = resultSet.getInt("fac_id");
                    String name = resultSet.getString("fac_name");
                    int telephone = resultSet.getInt("fac_tele");
                    String email = resultSet.getString("fac_email");

                    faculties.add(new Faculty(id,name,telephone,email));
                }
            }
        }

        return faculties;
    }

    /**
     * Retrieve faculty with given id
     * @param facultyId the id of the faculty to retrieve
     * @return Faculty if record exists null otherwise
     */
    public Faculty getFacultyById(int facultyId) throws SQLException, NamingException {
        String sql = "SELECT * FROM faculty WHERE fac_id=?";
        Faculty faculty = null;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setInt(1,facultyId);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet!=null){
                while (resultSet.next()){
                    String name = resultSet.getString("fac_name");
                    int telephone = resultSet.getInt("fac_tele");
                    String email = resultSet.getString("fac_email");

                    faculty = new Faculty(facultyId,name,telephone,email);
                }
            }
        }

        return faculty;
    }

    /**
     * Delete faculty with given id from the database
     * @param facultyId the id of the faculty to delete
     * @return true if removed false otherwise
     */
    public boolean deleteFaculty(int facultyId) throws SQLException, NamingException {
        String sql = "DELETE FROM faculty WHERE fac_id=?";
        boolean deleted = false;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setInt(1,facultyId);
            deleted = pstm.executeUpdate() == 1;
        }
        return deleted;
    }

}
