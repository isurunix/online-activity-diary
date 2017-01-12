package lk.grp.synergy.control;

import lk.grp.synergy.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by isuru on 1/12/17.
 */
public interface StudentControllerInterface {

    public ArrayList<Student> getAllStudents() throws SQLException;
    public Student getStudent(String id) throws SQLException;
    public Student updateStudent(Student student) throws SQLException;

}
