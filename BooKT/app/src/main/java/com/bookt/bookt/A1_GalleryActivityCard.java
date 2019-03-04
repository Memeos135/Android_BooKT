package com.bookt.bookt;

// A1_GalleryActivity CardView Setter
class A1_GalleryActivityCard {

    private String restaurantType;
    private String restaurantTypeImage;

    A1_GalleryActivityCard() {
    }

    public String getRestaurantTypeName() {
        return restaurantType;
    }

    public void setRestaurantTypeName(String name) {
        restaurantType = name;
    }

    public String getRestaurantTypeImage() {
        return restaurantTypeImage;
    }

    public void setRestaurantTypeImage(String image) {
        restaurantTypeImage = image;
    }

    A1_GalleryActivityCard(String name, String image) {
        restaurantType = name;
        restaurantTypeImage = image;
    }
}
