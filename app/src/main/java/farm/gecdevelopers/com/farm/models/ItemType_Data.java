package farm.gecdevelopers.com.farm.models;

public class ItemType_Data {


    private String name, description, date, manufacturer, itemId;

    public ItemType_Data(String itemId, String name, String description, String date, String manufacturer) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.manufacturer = manufacturer;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setDate(String date) {
        this.date = date;

    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

}
