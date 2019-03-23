package com.bookt.bookt;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Reviews implements Parcelable {

    private long reviewCount = 0;

    protected Reviews(Parcel in) {
        reviewCount = in.readLong();
    }

    public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel in) {
            return new Reviews(in);
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(reviewCount);
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Reviews() {
    }

    public Reviews(long reviewCount) {
        this.reviewCount = reviewCount;
    }
}
