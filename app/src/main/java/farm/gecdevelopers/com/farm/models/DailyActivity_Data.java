package farm.gecdevelopers.com.farm.models;

public class DailyActivity_Data {

    private String dailyAcitvityID;
    private String plotName;
    private String imageName, docName, videoName, details, comments, farmLatitude, farmLongitude, dateAndTime, hect;
    private String status, userId, reason;
    private String activityName;

    public DailyActivity_Data(String dailyAcitvityID, String plotName, String imageName,
                              String docName, String videoName, String details,
                              String comments, String farmLatitude, String farmLongitude,
                              String dateAndTime, String hect, String status,
                              String userId, String reason, String actid) {
        this.dailyAcitvityID = dailyAcitvityID;
        this.plotName = plotName;
        this.imageName = imageName;
        this.docName = docName;
        this.videoName = videoName;
        this.details = details;
        this.comments = comments;
        this.farmLatitude = farmLatitude;
        this.farmLongitude = farmLongitude;
        this.dateAndTime = dateAndTime;
        this.hect = hect;
        this.status = status;
        this.userId = userId;
        this.reason = reason;
        this.activityName = actid;
    }

    public String getDailyAcitvityID() {

        return dailyAcitvityID;
    }

    public void setDailyAcitvityID(String dailyAcitvityID) {
        this.dailyAcitvityID = dailyAcitvityID;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getActid() {
        return activityName;
    }

    public void setActid(String actid) {
        this.activityName = actid;
    }
}
