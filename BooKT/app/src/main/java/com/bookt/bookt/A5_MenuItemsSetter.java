package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;

class A5_MenuItemsSetter implements Parcelable {
    private String foodImage;
    private String foodTitle;
    private String foodDescription;
    private String foodPrice;

    A5_MenuItemsSetter(String foodTitle, String foodDescription, String foodImage, String foodPrice) {
        this.foodTitle = foodTitle;
        this.foodDescription = foodDescription;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
    }

    protected A5_MenuItemsSetter(Parcel in) {
        foodImage = in.readString();
        foodTitle = in.readString();
        foodDescription = in.readString();
        foodPrice = in.readString();
    }

    public static final Creator<A5_MenuItemsSetter> CREATOR = new Creator<A5_MenuItemsSetter>() {
        @Override
        public A5_MenuItemsSetter createFromParcel(Parcel in) {
            return new A5_MenuItemsSetter(in);
        }

        @Override
        public A5_MenuItemsSetter[] newArray(int size) {
            return new A5_MenuItemsSetter[size];
        }
    };

    public void setFoodTitle(String foodTitle){
        this.foodTitle = foodTitle;
    }

    public void setFoodDescription(String foodDescription){
        this.foodDescription = foodDescription;
    }

    public void setFoodPrice(String foodPrice){
        this.foodPrice = foodPrice;
    }

    public void setFoodImage(String foodImage){
        this.foodImage = foodImage;
    }

    public String getFoodTitle(){
        return foodTitle;
    }

    public String getFoodDescription(){
        return foodDescription;
    }

    public String getFoodImage(){
        return foodImage;
    }

    public String getFoodPrice(){
        return foodPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodImage);
        parcel.writeString(foodTitle);
        parcel.writeString(foodDescription);
        parcel.writeString(foodPrice);
    }
}
