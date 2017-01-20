package lk.grp.synergy.control;

import lk.grp.synergy.model.Course;

/**
 * Created by isuru on 1/19/17.
 */
public interface CourseControllerInterface {

    /**
     * Get course details for a given course code
     * @param courseCode the code of the course to retrieve
     * @return instance of Course with data if code is correct, null otherwise
     */
    public Course getCourse(String courseCode);

    /**
     * remove an existing course
     * @param courseCode course code of the course to remove
     * @return true if removed, false otherwise
     */
    public boolean removeCourse(String courseCode);

    /**
     * Update course details
     * @param course Course with updated details
     * @return true if updated, false otherwise
     */
    public boolean updateCourse(Course course);

}
