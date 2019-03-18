package com.bookt.bookt;

public class A0_ReservationsHistorySetter {
    private String date;
    private String time;
    private String location;
    private String restaurantName;

    public A0_ReservationsHistorySetter(String date, String time, String location, String restaurantName) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.restaurantName = restaurantName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
