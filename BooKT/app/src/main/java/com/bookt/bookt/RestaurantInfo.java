package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantInfo implements Parcelable {

    private String restaurant_location;
    private String restaurant_name;
    private String restaurant_open;
    private String restaurant_close;
    private String restaurant_price;
    private String status;
    private String restaurant_image;
    private String firebase_id;

    protected RestaurantInfo(Parcel in) {
        restaurant_location = in.readString();
        restaurant_name = in.readString();
        restaurant_open = in.readString();
        restaurant_close = in.readString();
        restaurant_price = in.readString();
        status = in.readString();
        restaurant_image = in.readString();
        firebase_id = in.readString();
    }

    public static final Creator<RestaurantInfo> CREATOR = new Creator<RestaurantInfo>() {
        @Override
        public RestaurantInfo createFromParcel(Parcel in) {
            return new RestaurantInfo(in);
        }

        @Override
        public RestaurantInfo[] newArray(int size) {
            return new RestaurantInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void setFirebase_id(String firebase_id) {
        this.firebase_id = firebase_id;
    }

    public String getFirebase_id() {
        return firebase_id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(restaurant_location);
        dest.writeString(restaurant_name);
        dest.writeString(restaurant_open);
        dest.writeString(restaurant_close);
        dest.writeString(restaurant_price);
        dest.writeString(status);
        dest.writeString(restaurant_image);
        dest.writeString(firebase_id);
    }

    public String getRestaurant_location() {
        return restaurant_location;
    }

    public void setRestaurant_location(String restaurant_location) {
        this.restaurant_location = restaurant_location;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_open() {
        return restaurant_open;
    }

    public void setRestaurant_open(String restaurant_open) {
        this.restaurant_open = restaurant_open;
    }

    public String getRestaurant_close() {
        return restaurant_close;
    }

    public void setRestaurant_close(String restaurant_close) {
        this.restaurant_close = restaurant_close;
    }

    public String getRestaurant_price() {
        return restaurant_price;
    }

    public void setRestaurant_price(String restaurant_price) {
        this.restaurant_price = restaurant_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRestaurant_image() {
        return restaurant_image;
    }

    public void setRestaurant_image(String restaurant_image) {
        this.restaurant_image = restaurant_image;
    }

    public RestaurantInfo() {
    }

    public RestaurantInfo(String restaurant_location, String restaurant_name, String restaurant_open, String restaurant_close, String restaurant_price, String status, String restaurant_image) {
        this.restaurant_location = restaurant_location;
        this.restaurant_name = restaurant_name;
        this.restaurant_open = restaurant_open;
        this.restaurant_close = restaurant_close;
        this.restaurant_price = restaurant_price;
        this.status = status;
        this.restaurant_image = restaurant_image;
    }
}
