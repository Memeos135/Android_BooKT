package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;

// A2_RestaurantsActivity CardView Setter
public class A2_RestaurantsActivityCard implements Parcelable {
    private String firebaseId;
    private String restaurant_name;
    private String restaurant_location;
    private String restaurant_open;
    private String restaurant_close;
    private String restaurant_price;
    private String restaurant_reviews;
    private String restaurant_status;
    private String restaurant_cuisine;

    public A2_RestaurantsActivityCard(){};

    protected A2_RestaurantsActivityCard(Parcel in) {
        firebaseId = in.readString();
        restaurant_name = in.readString();
        restaurant_location = in.readString();
        restaurant_open = in.readString();
        restaurant_close = in.readString();
        restaurant_price = in.readString();
        restaurant_reviews = in.readString();
        restaurant_status = in.readString();
        restaurant_cuisine = in.readString();
    }

    public static final Creator<A2_RestaurantsActivityCard> CREATOR = new Creator<A2_RestaurantsActivityCard>() {
        @Override
        public A2_RestaurantsActivityCard createFromParcel(Parcel in) {
            return new A2_RestaurantsActivityCard(in);
        }

        @Override
        public A2_RestaurantsActivityCard[] newArray(int size) {
            return new A2_RestaurantsActivityCard[size];
        }
    };

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setRestaurant_location(String restaurant_location) {
        this.restaurant_location = restaurant_location;
    }

    public void setRestaurant_open(String restaurant_open) {
        this.restaurant_open = restaurant_open;
    }

    public void setRestaurant_close(String restaurant_close) {
        this.restaurant_close = restaurant_close;
    }

    public void setRestaurant_price(String restaurant_price) {
        this.restaurant_price = restaurant_price;
    }

    public void setRestaurant_reviews(String restaurant_reviews) {
        this.restaurant_reviews = restaurant_reviews;
    }

    public void setRestaurant_status(String restaurant_status) {
        this.restaurant_status = restaurant_status;
    }

    public void setRestaurant_cuisine(String restaurant_cuisine){
        this.restaurant_cuisine = restaurant_cuisine;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getRestaurant_location() {
        return restaurant_location;
    }

    public String getRestaurant_open() {
        return restaurant_open;
    }

    public String getRestaurant_close() {
        return restaurant_close;
    }

    public String getRestaurant_price() {
        return restaurant_price;
    }

    public String getRestaurant_reviews() {
        return restaurant_reviews;
    }

    public String getRestaurant_status() {
        return restaurant_status;
    }

    public String getRestaurant_cuisine(){
        return restaurant_cuisine;
    }

    public A2_RestaurantsActivityCard(String firebaseId, String restaurant_name, String restaurant_location, String restaurant_open, String restaurant_close, String restaurant_price, String restaurant_reviews, String restaurant_status, String restaurant_cuisine) {
        this.firebaseId = firebaseId;
        this.restaurant_name = restaurant_name;
        this.restaurant_location = restaurant_location;
        this.restaurant_open = restaurant_open;
        this.restaurant_close = restaurant_close;
        this.restaurant_price = restaurant_price;
        this.restaurant_reviews = restaurant_reviews;
        this.restaurant_status = restaurant_status;
        this.restaurant_cuisine = restaurant_cuisine;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firebaseId);
        parcel.writeString(restaurant_name);
        parcel.writeString(restaurant_location);
        parcel.writeString(restaurant_open);
        parcel.writeString(restaurant_close);
        parcel.writeString(restaurant_price);
        parcel.writeString(restaurant_reviews);
        parcel.writeString(restaurant_status);
        parcel.writeString(restaurant_cuisine);
    }
}