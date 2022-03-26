package com.example.nolfi.model;

import android.net.Uri;

public class MainItem {
    Uri product_image;
    String product_name;
    String living_area;
    String time;
    String product_price;
    String product_category;

    public MainItem(Uri product_image, String product_name, String living_area, String time, String product_price, String product_category) {
        this.product_image=product_image;
        this.product_name=product_name;
        this.living_area=living_area;
        this.time=time;
        this.product_price=product_price;
        this.product_category=product_category;
    }

    public Uri getProduct_image() {
        return product_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getLiving_area() {
        return living_area;
    }

    public String getTime() {
        return time;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_image(Uri product_image) {
        this.product_image=product_image;
    }

    public void setProduct_name(String product_name) {
        this.product_name=product_name;
    }

    public void setLiving_area(String living_area) {
        this.living_area=living_area;
    }

    public void setTime(String time) {
        this.time=time;
    }

    public void setProduct_price(String product_price) {
        this.product_price=product_price;
    }

    public void setProduct_category(String product_category) {
        this.product_category=product_category;
    }
}