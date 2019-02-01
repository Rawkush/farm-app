package farm.gecdevelopers.com.farm.models;

public class FarmActivity {
    String activity, desc;

    public FarmActivity(String activity, String desc) {

        this.activity = activity;
        this.desc = desc;

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