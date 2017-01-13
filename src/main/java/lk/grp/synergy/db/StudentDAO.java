package lk.grp.synergy.db;

import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by isuru on 1/13/17.
 */
public class StudentDAO {

    public StudentDAO(){

    }

    public ArrayList<Student> getAllStudents() throws SQLException, NamingException {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try(
            Connection con = DBConnector.getConnection();
            PreparedStatement prStmt = con.prepareStatement(sql)
        ){
            ResultSet resultSet = prStmt.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    int id = resultSet.getInt("student_id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    int telephone = resultSet.getInt("telephone");
                    int notificationFreq = resultSet.getInt("notif_freq_level");
                    int channel = resultSet.getInt("notif_channel");

                    students.add(new Student(id,name,email,telephone,notificationFreq,channel));
                }
            }
        }

        return students;
    }

    /**
     * Retrieve student with given id
     * @param studentId the id of the student to retrieve
     * @return Student if record exists null otherwise
     */
    public Student getStudentById(int studentId) throws SQLException, NamingException {
        String sql = "SELECT * FROM student WHERE student_id=?";
        Student student = null;

        try(
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ){
            pstmt.setInt(1,studentId);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet!=null){
                while (resultSet.next()){
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    int telephone = resultSet.getInt("telephone");
                    int notificationFreq = resultSet.getInt("notif_freq_level");
                    int channel = resultSet.getInt("notif_channel");

                    student = new Student(studentId,name,email,telephone,notificationFreq,channel);
                }
            }
        }

        return student;
    }

    /**
     * Delete student with given id from the database
     * @param studentId the id of the student to delete
     * @return true if removed false otherwise
     */
    public boolean deleteStudent(int studentId) throws SQLException, NamingException {
        String sql = "DELETE FROM student WHERE student_id=?";
        boolean deleted = false;

        try(
            Connection con = DBConnector.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql)
        ){
            pstm.setInt(1,studentId);
            deleted = pstm.executeUpdate() == 1;
        }
        return deleted;
    }

}
