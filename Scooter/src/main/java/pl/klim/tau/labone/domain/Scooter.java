package pl.klim.tau.labone.domain;

import java.util.Date;


public class Scooter {
    private int id;
    private Date updated;
    private Date created;
    private Date readed;

    private String model;
    private String brand;
    private int productionYear;
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getReaded() {
        return readed;
    }

    public void setReaded(Date readed) {
        this.readed = readed;
    }

    public Scooter (int id, String model, String brand, int productionYear, String color) {
        this.model = model;
        this.brand = brand;
        this.productionYear = productionYear;
        this.color = color;
        this.id = id;
    }


}
