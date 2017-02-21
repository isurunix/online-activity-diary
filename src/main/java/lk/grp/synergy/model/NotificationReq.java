package lk.grp.synergy.model;

/**
 * Created by isuru on 2/14/17.
 */
public class NotificationReq {
    private int id;
    private String courseCode;
    private String msg;
    private int processed;

    public NotificationReq() {
    }

    public NotificationReq(int id, String courseCode, String msg, int processed) {
        this.id = id;
        this.courseCode = courseCode;
        this.msg = msg;
        this.processed = processed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }
}
