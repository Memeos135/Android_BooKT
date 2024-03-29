package com.bookt.bookt;

public class A0_ReservationsHistorySetter {
    private String date;
    private String time;
    private String location;
    private String restaurantName;

    private String name;
    private String email;
    private String mobile;
    private String additional_info;
    private String year;
    private String month;
    private String day;
    private String hour;
    private String sections;

    public A0_ReservationsHistorySetter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public A0_ReservationsHistorySetter(String date, String location, String restaurantName, String name, String email, String mobile, String additional_info, String year, String month, String day, String hour, String sections) {
        this.date = date;
        this.location = location;
        this.restaurantName = restaurantName;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.additional_info = additional_info;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.sections = sections;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
