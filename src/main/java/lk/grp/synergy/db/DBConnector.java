package lk.grp.synergy.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by isuru on 1/12/17.
 *
 * This class provides DBConnector with DB pooling
 * Use connections for every database connection used within the application
 */
public class DBConnector {

    public static Connection getConnection() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        String env = System.getProperty("ENV");
        Context context = (Context) initialContext.lookup("java:comp/env");

        //The JDBC Data source that we just created
        DataSource ds = (DataSource) context.lookup("connpool");
        Connection connection = ds.getConnection();

        if (connection == null)
        {
            throw new SQLException("Error establishing connection!");
        }

        return connection;
    }

}

