package lk.grp.synergy.model;

/**
 * Created by isuru on 1/12/17.
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private String telephone;
    private int notificationFrequency;
    private int notificationChannel;

    public Student(String studentId, String name, String email, String telephone, int notificationFrequency, int notificationChannel) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.notificationFrequency = notificationFrequency;
        this.notificationChannel = notificationChannel;
    }

    public Student() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getNotificationFrequency() {
        return notificationFrequency;
    }

    public void setNotificationFrequency(int notificationFrequency) {
        this.notificationFrequency = notificationFrequency;
    }

    public int getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(int notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return studentId.equals(student.studentId);

    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}
