package farm.gecdevelopers.com.farm.models;

public class Farms {
    String size, name, desc;

    public Farms(String size, String name, String desc) {
        this.size = size;
        this.name = name;
        this.desc = desc;
    }

    public String getSize() {

        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}