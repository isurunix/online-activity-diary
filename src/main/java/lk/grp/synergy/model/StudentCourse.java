package lk.grp.synergy.model;

public class StudentCourse {
  private int student_id;
  private String course_code;
  private String group;

  public StudentCourse(int studentId, String courseCode, String group) {
    this.student_id = studentId;
    this.course_code = courseCode;
    this.group = group;
  }

  public int getStudent_id() {
    return student_id;
  }

  public void setStudent_id(int student_id) {
    this.student_id = student_id;
  }

  public String getCourse_code() {
    return course_code;
  }

  public void setCourse_code(String course_code) {
    this.course_code = course_code;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }
}
