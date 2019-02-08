package com.bookt.myapplication;

public class Restaurants {

    String name;
    String location;
    String type;
    int price;
    String workingHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public Restaurants() {
    }

    public Restaurants(String name, String location, String type, int price, String workingHours) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.price = price;
        this.workingHours = workingHours;
    }
}
