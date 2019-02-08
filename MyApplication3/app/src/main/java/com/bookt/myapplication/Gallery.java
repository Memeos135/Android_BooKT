package com.bookt.myapplication;

public class Gallery {

    private String name;
    private String urlToImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Gallery() {
    }

    public Gallery(String name, String urlToImage) {
        this.name = name;
        this.urlToImage = urlToImage;
    }
}
