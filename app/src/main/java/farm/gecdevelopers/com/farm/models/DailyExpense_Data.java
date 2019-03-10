package farm.gecdevelopers.com.farm.models;

import farm.gecdevelopers.com.farm.activity.admin.DailyExpense;

public class DailyExpense_Data {

    private String id, unit, unitPrice, total, userId;
    private String plotName;
    private String purpose,supplier, description,dateAndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public DailyExpense_Data(String id, String unit, String unitPrice,
                             String total, String userId, String plotName,
                             String purpose, String supplier,
                             String description, String dateAndTime) {
        this.id = id;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.total = total;
        this.userId = userId;
        this.plotName = plotName;
        this.purpose = purpose;
        this.supplier = supplier;
        this.description = description;
        this.dateAndTime = dateAndTime;
    }
    public DailyExpense_Data(){}
}
