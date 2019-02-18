package com.bookt.bookt;

import android.widget.ImageView;

public class ReviewsCard {

    private String date;
    private String name;
    private double stars;
    private ImageView userImage;
    private String review;

    ReviewsCard(String name, double stars, String date, String review, ImageView userImage){
        this.name = name;
        this.stars = stars;
        this.date = date;
        this.review = review;
        this.userImage = userImage;
    }

    public double getStars(){
        return stars;
    }

    public String getDate(){
        return date;
    }

    public String getName(){
        return name;
    }

    public ImageView getUserImage(){
        return userImage;
    }

    public String getReview(){
        return review;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setStars(double stars){
        this.stars = stars;
    }

    public void setUserImage(ImageView userImage){
        this.userImage = userImage;
    }

    public void setReview(String review){
        this.review = review;
    }
}
