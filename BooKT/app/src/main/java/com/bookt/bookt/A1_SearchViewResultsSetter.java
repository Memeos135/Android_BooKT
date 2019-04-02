package com.bookt.bookt;

public class A1_SearchViewResultsSetter {

    private String name;
    private String location;
    private String cuisine;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public A1_SearchViewResultsSetter() {
    }

    public A1_SearchViewResultsSetter(String name, String location, String cuisine, String key) {
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.key = key;
    }
}
