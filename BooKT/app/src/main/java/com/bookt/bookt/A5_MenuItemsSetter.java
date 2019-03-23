package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.MenuItem;

import java.util.ArrayList;

class A5_MenuItemsSetter implements Parcelable {
    private String menuCategory;
    private ArrayList<MenuItemElement> menuItems;

    protected A5_MenuItemsSetter(Parcel in) {
        menuCategory = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menuCategory);
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public ArrayList<MenuItemElement> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItemElement> menuItems) {
        this.menuItems = menuItems;
    }

    public A5_MenuItemsSetter() {
    }

    public A5_MenuItemsSetter(String menuCategory, ArrayList<MenuItemElement> menuItems) {
        this.menuCategory = menuCategory;
        this.menuItems = menuItems;
    }
}
