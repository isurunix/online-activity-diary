package lk.grp.synergy.model;

/**
 * Created by isuru on 1/12/17.
 */
public class Course {
    private String courseCode;
    private String name;
    private String courseCoordinator;
    private String academicCoordinator;

    public Course(String courseCode, String name, String courseCoordinator, String academicCoordinator) {
        this.courseCode = courseCode;
        this.name = name;
        this.courseCoordinator = courseCoordinator;
        this.academicCoordinator = academicCoordinator;
    }

    public Course(String courseCode, String name) {
        this.courseCode = courseCode;
        this.name = name;
    }

    public Course() {

    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCoordinator() {
        return courseCoordinator;
    }

    public void setCourseCoordinator(String courseCoordinator) {
        this.courseCoordinator = courseCoordinator;
    }

    public String getAcademicCoordinator() {
        return academicCoordinator;
    }

    public void setAcademicCoordinator(String academicCoordinator) {
        this.academicCoordinator = academicCoordinator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseCode != null ? courseCode.equals(course.courseCode) : course.courseCode == null;

    }

    @Override
    public int hashCode() {
        return courseCode != null ? courseCode.hashCode() : 0;
    }
}
