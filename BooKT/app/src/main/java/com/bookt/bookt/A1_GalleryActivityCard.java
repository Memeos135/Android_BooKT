package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;

// A1_GalleryActivity CardView Setter
class A1_GalleryActivityCard implements Parcelable {
    String type;

    public A1_GalleryActivityCard(String type) {
        this.type = type;
    }

    protected A1_GalleryActivityCard(Parcel in) {
        type = in.readString();
    }

    public static final Creator<A1_GalleryActivityCard> CREATOR = new Creator<A1_GalleryActivityCard>() {

        @Override
        public A1_GalleryActivityCard createFromParcel(Parcel in) {
            return new A1_GalleryActivityCard(in);
        }

        @Override
        public A1_GalleryActivityCard[] newArray(int size) {
            return new A1_GalleryActivityCard[size];
        }
    };

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public A1_GalleryActivityCard() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
    }
}
