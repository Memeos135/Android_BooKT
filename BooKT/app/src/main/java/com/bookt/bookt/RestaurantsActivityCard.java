package com.bookt.bookt;

public class RestaurantsActivityCard {

    private String restaurantName;
    private String restaurantSubCategory;
    private String restaurantLocation;
    private int restaurantPriceRange;
    private String restaurantOpenHour;
    private String restaurantCloseHour;
    private String restaurantImage;

    RestaurantsActivityCard(String restaurantName, String restaurantSubCategory, String restaurantLocation,
                            int restaurantPriceRange, String restaurantOpenHour, String restaurantCloseHour){
        this.restaurantName = restaurantName;
        this.restaurantSubCategory = restaurantSubCategory;
        this.restaurantLocation = restaurantLocation;
        this. restaurantPriceRange = restaurantPriceRange;
        this.restaurantOpenHour = restaurantOpenHour;
        this.restaurantCloseHour = restaurantCloseHour;

    }

    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public void setRestaurantSubCategory(String restaurantSubCategory){
        this.restaurantSubCategory = restaurantSubCategory;
    }

    public void setRestaurantLocation(String restaurantLocation){
        this.restaurantLocation = restaurantLocation;
    }

    public void setRestaurantPriceRange(int restaurantPriceRange){
        this.restaurantPriceRange = restaurantPriceRange;
    }

    public void setRestaurantOpenHour(String restaurantOpenHour){
        this.restaurantOpenHour = restaurantOpenHour;
    }

    public void setRestaurantCloseHour(String restaurantCloseHour){
        this.restaurantCloseHour = restaurantCloseHour;
    }

    public void setRestaurantImage(String restaurantImage){
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantName(){
        return this.restaurantName;
    }

    public String getRestaurantSubCategory(){
        return this.restaurantSubCategory;
    }

    public String getRestaurantLocation(){
        return this.restaurantLocation;
    }

    public int getRestaurantPriceRange() {
        return this.restaurantPriceRange;
    }

    public String getRestaurantOpenHour(){
        return this.restaurantOpenHour;
    }

    public String getRestaurantCloseHour(){
        return this.restaurantCloseHour;
    }
}
