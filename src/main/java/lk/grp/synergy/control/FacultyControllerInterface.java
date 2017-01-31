package lk.grp.synergy.control;

import lk.grp.synergy.model.Faculty;

import java.sql.SQLException;

/**
 * Created by isuru on 1/19/17.
 */
public interface FacultyControllerInterface {

    public void addFaculty(Faculty faculty) throws SQLException;
    public void getAllFaculties() throws SQLException;
    public void getFaculty(int facId) throws SQLException;

}
