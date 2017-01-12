package lk.grp.synergy.model;

/**
 * Created by isuru on 1/12/17.
 */
public class Department {
    private int deptId;
    private String deptName;
    private String deptEmail;
    private String deptTelephone;

    public Department(int deptId, String deptName, String deptEmail, String deptTelephone) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptEmail = deptEmail;
        this.deptTelephone = deptTelephone;
    }

    public Department(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Department() {
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptEmail() {
        return deptEmail;
    }

    public void setDeptEmail(String deptEmail) {
        this.deptEmail = deptEmail;
    }

    public String getDeptTelephone() {
        return deptTelephone;
    }

    public void setDeptTelephone(String deptTelephone) {
        this.deptTelephone = deptTelephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return deptId == that.deptId;

    }

    @Override
    public int hashCode() {
        return deptId;
    }
}
