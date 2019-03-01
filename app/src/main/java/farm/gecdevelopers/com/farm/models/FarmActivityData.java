package farm.gecdevelopers.com.farm.models;

public class FarmActivityData {
    String activity;
    String desc;
    String id;

    public FarmActivityData(String activity, String id, String desc) {

        this.activity = activity;
        this.desc = desc;
        this.id = id;
    }

    public FarmActivityData(String activity, String desc) {

        this.activity = activity;
        this.desc = desc;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}