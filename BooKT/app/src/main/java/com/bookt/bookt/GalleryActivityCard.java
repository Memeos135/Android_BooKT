package com.bookt.bookt;

// GalleryActivity CardView Setter
class GalleryActivityCard {

    private String restaurantType;
    private String restaurantTypeImage;

    GalleryActivityCard() {
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

    GalleryActivityCard(String name, String image) {
        restaurantType = name;
        restaurantTypeImage = image;
    }
}
