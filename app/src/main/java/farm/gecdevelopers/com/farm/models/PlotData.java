package farm.gecdevelopers.com.farm.models;

public class PlotData {
    private String size, plotname, desc, manager, location, farmId;

    public PlotData(String plotname, String farmId) {
        this.plotname = plotname;
        this.farmId = farmId;
    }

    public PlotData(String size, String plotname, String desc, String manager, String location) {
        this.size = size;
        this.plotname = plotname;
        this.desc = desc;
        this.location = location;
        this.manager = manager;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getSize() {

        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPlotname() {
        return plotname;
    }

    public void setPlotname(String plotname) {
        this.plotname = plotname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public String getManager() {

        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }  public String getLocation() {

    return location;
}

    public void setLocation(String location) {
        this.location = location;
    }


}