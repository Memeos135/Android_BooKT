package com.bookt.bookt;

// GalleryActivity CardView Setter
public class GalleryActivityCard {

    String restaurantType;
    String restaurantTypeImage;

    public GalleryActivityCard() {
    }

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

    public GalleryActivityCard(String name, String image) {
        this.restaurantType = name;
        this.restaurantTypeImage = image;
    }
}
