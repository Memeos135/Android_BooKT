package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

// A2_RestaurantsActivity CardView Setter
public class A2_RestaurantsActivityCard implements Parcelable {

    private RestaurantInfo restaurant_info;
    private Reviews reviews;

    protected A2_RestaurantsActivityCard(Parcel in) {
        restaurant_info = in.readParcelable(RestaurantInfo.class.getClassLoader());
        reviews = in.readParcelable(Reviews.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(restaurant_info, flags);
        dest.writeParcelable(reviews, flags);
    }

    public RestaurantInfo getRestaurant_info() {
        return restaurant_info;
    }

    public void setRestaurant_info(RestaurantInfo restaurant_info) {
        this.restaurant_info = restaurant_info;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public A2_RestaurantsActivityCard() {
    }

    public A2_RestaurantsActivityCard(RestaurantInfo restaurant_info, Reviews reviews) {
        this.restaurant_info = restaurant_info;
        this.reviews = reviews;
    }

}