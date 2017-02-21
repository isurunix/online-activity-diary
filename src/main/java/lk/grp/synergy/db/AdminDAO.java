package lk.grp.synergy.db;

import lk.grp.synergy.model.Admin;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by isuru on 2/12/17.
 */
public class AdminDAO {

    public AdminDAO() {
    }

    public Admin getAdminByUsername(String userName) throws SQLException, NamingException {
        String sql = "SELECT * FROM admin WHERE username=?";
        Admin admin = null;

        try(
                Connection con = DBConnector.getConnection();
                PreparedStatement psmt = con.prepareStatement(sql)
        ){
            psmt.setString(1,userName);
            ResultSet resultSet = psmt.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String pwd = resultSet.getString("password");
                admin = new Admin(id,userName,pwd);
            }
        }
        return admin;
    }
}
