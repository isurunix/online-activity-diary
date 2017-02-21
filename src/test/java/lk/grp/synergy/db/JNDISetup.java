package lk.grp.synergy.db;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Created by isuru on 2/13/17.
 */
public class JNDISetup {

    /**
     * Setup the Data Source
     */
    public static void doSetup(String ds_name) {
        try {
            InitialContext ctxt = new InitialContext();
            DataSource ds = (DataSource) ctxt.lookup("jdbc."+ds_name);
            // rebind for alias if needed
            ctxt.rebind("jdbc/"+ds_name, ds);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
