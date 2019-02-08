package com.bookt.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String title;
    private String subTitle;
    private String price;
    private String image;



    protected Item(Parcel in) {
        title = in.readString();
        subTitle = in.readString();
        price = in.readString();
        image = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Item() {
    }

    public Item(String title, String subTitle, String price, String image) {
        this.title = title;
        this.subTitle = subTitle;
        this.price = price;
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(subTitle);
        dest.writeString(price);
        dest.writeString(image);
    }
}
