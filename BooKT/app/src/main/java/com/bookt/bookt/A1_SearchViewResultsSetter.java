package com.bookt.bookt;

public class A1_SearchViewResultsSetter {

    private String restaurantName;
    private String district;

    A1_SearchViewResultsSetter(String restaurantName, String district) {
        this.restaurantName = restaurantName;
        this.district = district;
    }

    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public void setDistrict(String district){
        this.district = district;
    }

    public String getRestaurantName(){
        return restaurantName;
    }

    public String getDistrict(){
        return district;
    }
}
