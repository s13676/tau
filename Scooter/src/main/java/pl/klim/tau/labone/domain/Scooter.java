package pl.klim.tau.labone.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Scooter {
    private int id;
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

    public Scooter (int id, String model, String brand, int productionYear, String color) {
        this.model = model;
        this.brand = brand;
        this.productionYear = productionYear;
        this.color = color;
        this.id = id;
    }


}
