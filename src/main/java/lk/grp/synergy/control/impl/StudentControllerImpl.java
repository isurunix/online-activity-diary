package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.StudentControllerInterface;
import lk.grp.synergy.db.ActivityDAO;
import lk.grp.synergy.db.CourseDAO;
import lk.grp.synergy.db.StudentCourseDAO;
import lk.grp.synergy.db.StudentDAO;
import lk.grp.synergy.model.Activity;
import lk.grp.synergy.model.Course;
import lk.grp.synergy.model.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by isuru on 1/14/17.
 */
public class StudentControllerImpl implements StudentControllerInterface {
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private ActivityDAO activityDAO;
    private StudentCourseDAO studentCourseDAO;

    public StudentControllerImpl(){
        studentDAO = new StudentDAO();
        studentCourseDAO = new StudentCourseDAO();
        courseDAO = new CourseDAO();
        activityDAO = new ActivityDAO();
        studentCourseDAO = new StudentCourseDAO();
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException, NamingException {
        ArrayList<Student> allStudents = studentDAO.getAllStudents();
        return allStudents;
    }

    @Override
    public Student getStudent(String id) throws SQLException, NamingException {
        Student student = null;
        student = studentDAO.getStudentById(Integer.parseInt(id));
        return student;
    }

    @Override
    public Student updateStudent(Student student) throws SQLException {

        return null;
    }

    @Override
    public List<Course> getCourseList(String studentId) throws SQLException, NamingException {
        List<String> courseList = studentCourseDAO.getCourseList(Integer.parseInt(studentId));
        ArrayList<Course> courses = new ArrayList<>();
        for(String courseCode : courseList){
            Course course = courseDAO.getCourseBycode(courseCode);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Activity> getActivities(int studentId) throws SQLException, NamingException{
        List<String> courseList = studentCourseDAO.getCourseList(studentId);
        List<Activity> activities = new ArrayList<>();
        for(String courseCode : courseList) {
            activities.addAll(activityDAO.getAllActivities(courseCode));
        }

        Collections.sort(activities, (Activity a1, Activity a2) -> {
            LocalDateTime t1 = a1.getDate().atTime(a1.getStartTime());
            LocalDateTime t2 = a2.getDate().atTime(a2.getStartTime());
            return (t1.compareTo(t2));
        });

        return activities;
    }

    @Override
    public boolean updateStudentProfileInfo(Student student) throws SQLException, NamingException {
        return studentDAO.updateStudent(student);
    }



}
