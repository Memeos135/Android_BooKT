package com.bookt.bookt;

public class GalleryActivityCard {

    String restaurantType;
    String restaurantTypeImage;

    public String getRestaurantTypeName() {
        return restaurantType;
    }

    public void setRestaurantTypeName(String name) {
        this.restaurantType = name;
    }

    public String getRestaurantTypeImage() {
        return restaurantTypeImage;
    }

    public void setRestaurantTypeImage(String image) {
        this.restaurantTypeImage = image;
    }

    public GalleryActivityCard() {
    }

    public GalleryActivityCard(String name, String image) {
        this.restaurantType = name;
        this.restaurantTypeImage = image;
    }
}