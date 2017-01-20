package lk.grp.synergy.control;

import lk.grp.synergy.model.Faculty;

/**
 * Created by isuru on 1/19/17.
 */
public interface FacultyControllerInterface {

    public void addFaculty(Faculty faculty);
    public void getAllFaculties();
    public void getFaculty(int facId);

}
