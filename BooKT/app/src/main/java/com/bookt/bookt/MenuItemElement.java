package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItemElement implements Parcelable {
    private String description;
    private String image;
    private String name;
    private String price;

    protected MenuItemElement(Parcel in) {
        description = in.readString();
        image = in.readString();
        name = in.readString();
        price = in.readString();
    }

    public static final Creator<MenuItemElement> CREATOR = new Creator<MenuItemElement>() {
        @Override
        public MenuItemElement createFromParcel(Parcel in) {
            return new MenuItemElement(in);
        }

        @Override
        public MenuItemElement[] newArray(int size) {
            return new MenuItemElement[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public MenuItemElement() {
    }

    public MenuItemElement(String description, String image, String name, String price) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(price);
    }
}
