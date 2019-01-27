package farm.gecdevelopers.com.farm.models;

public class DailyActivity_Data {

    private long dailyAcitvityID;
    private long farmID;
    private String imageName, docName, videoName, details, comments, farmLatitude, farmLongitude,dateAndTime, hect;
    private int status,userId, reason;
    private long actid;

    public DailyActivity_Data() {
    }

    public long getDailyAcitvityID() {
        return dailyAcitvityID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDailyAcitvityID(long dailyAcitvityID) {
        this.dailyAcitvityID = dailyAcitvityID;
    }

    public long getFarmID() {
        return farmID;
    }

    public void setFarmID(long farmID) {
        this.farmID = farmID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFarmLatitude() {
        return farmLatitude;
    }

    public void setFarmLatitude(String farmLatitude) {
        this.farmLatitude = farmLatitude;
    }

    public String getFarmLongitude() {
        return farmLongitude;
    }

    public void setFarmLongitude(String farmLongitude) {
        this.farmLongitude = farmLongitude;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getHect() {
        return hect;
    }

    public void setHect(String hect) {
        this.hect = hect;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public long getActid() {
        return actid;
    }

    public void setActid(long actid) {
        this.actid = actid;
    }
}
