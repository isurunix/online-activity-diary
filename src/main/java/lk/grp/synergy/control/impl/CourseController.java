package lk.grp.synergy.control.impl;

import lk.grp.synergy.control.CourseControllerInterface;
import lk.grp.synergy.model.Course;

/**
 * Created by USER on 1/24/2017.
 */
public class CourseController implements CourseControllerInterface {

    @Override
    public Course getCourse(String courseCode) {
        return null;
    }

    @Override
    public boolean removeCourse(String courseCode) {
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        return false;
    }
}
