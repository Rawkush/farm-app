package farm.gecdevelopers.com.farm.models;

public class Auditor {
    String manId,manName,manEmail,manPhnNo,manUsrname;

    public Auditor(String manId, String manName, String manEmail, String manPhnNo, String manUsrname) {
        this.manId = manId;
        this.manName = manName;
        this.manEmail = manEmail;
        this.manPhnNo = manPhnNo;
        this.manUsrname = manUsrname;
    }

    public String getManId() {

        return manId;
    }

    public void setManId(String manId) {
        this.manId = manId;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getManEmail() {
        return manEmail;
    }

    public void setManEmail(String manEmail) {
        this.manEmail = manEmail;
    }

    public String getManPhnNo() {
        return manPhnNo;
    }

    public void setManPhnNo(String manPhnNo) {
        this.manPhnNo = manPhnNo;
    }

    public String getManUsrname() {
        return manUsrname;
    }

    public void setManUsrname(String manUsrname) {
        this.manUsrname = manUsrname;
    }
}


