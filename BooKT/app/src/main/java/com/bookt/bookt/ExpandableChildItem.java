package com.bookt.bookt;

class ExpandableChildItem {
    private String foodImage;
    private String foodTitle;
    private String foodDescription;
    private String foodPrice;

    ExpandableChildItem(String foodTitle, String foodDescription, String foodImage, String foodPrice) {
        this.foodTitle = foodTitle;
        this.foodDescription = foodDescription;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
    }

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
}
