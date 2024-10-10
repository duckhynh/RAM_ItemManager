package data;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hung
 */
public class RAMItem implements Serializable{
    
    private static final long serialVersionUID = 1L; 
    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private String productMonthYear;
    private boolean active; 

    public RAMItem() {
    }

    public RAMItem(String code, String type, String bus, String brand, int quantity, String productMonthYear, boolean active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.productMonthYear = productMonthYear;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getBus() {
        return bus;
    }

    public String getBrand() {
        return brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductMonthYear() {
        return productMonthYear;
    }

    public boolean isActive() {
        return active;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductMonthYear(String productMonthYear) {
        this.productMonthYear = productMonthYear;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
    return String.format("RAMItem{%s, %s, %s, %s, %d, s, %b}",
                         code, type, bus, brand, quantity, productMonthYear, active);
}

    
}
