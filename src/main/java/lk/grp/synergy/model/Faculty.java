package lk.grp.synergy.model;

/**
 * Created by isuru on 1/12/17.
 */
public class Faculty {
    private int facId;
    private String facName;
    private String facEmail;
    private String facTelephone;

    public Faculty(int facId, String facName, String facEmail, String facTelephone) {
        this.facId = facId;
        this.facName = facName;
        this.facEmail = facEmail;
        this.facTelephone = facTelephone;
    }

    public Faculty(int facId, String facName) {
        this.facId = facId;
        this.facName = facName;
    }

    public Faculty() {

    }

    public int getFacId() {
        return facId;
    }

    public void setFacId(int facId) {
        this.facId = facId;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getFacEmail() {
        return facEmail;
    }

    public void setFacEmail(String facEmail) {
        this.facEmail = facEmail;
    }

    public String getFacTelephone() {
        return facTelephone;
    }

    public void setFacTelephone(String facTelephone) {
        this.facTelephone = facTelephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        return facId == faculty.facId;

    }

    @Override
    public int hashCode() {
        return facId;
    }
}
