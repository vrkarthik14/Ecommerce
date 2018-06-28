package com.example.codex_pc.ecommerce;

public class Product {

    String price,estimated_delivery,ratings,availabale_qty,produt_img_path,pro_name,serial_no,desc;

    public Product() {
    }

    public Product(String desc,String price, String estimated_delivery, String ratings, String availabale_qty, String produt_img_path, String pro_name, String serial_no) {
        this.price = price;
        this.desc = desc;
        this.estimated_delivery = estimated_delivery;
        this.ratings = ratings;
        this.availabale_qty = availabale_qty;
        this.produt_img_path = produt_img_path;
        this.pro_name = pro_name;
        this.serial_no = serial_no;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEstimated_delivery() {
        return estimated_delivery;
    }

    public void setEstimated_delivery(String estimated_delivery) {
        this.estimated_delivery = estimated_delivery;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getAvailabale_qty() {
        return availabale_qty;
    }

    public void setAvailabale_qty(String availabale_qty) {
        this.availabale_qty = availabale_qty;
    }

    public String getProdut_img_path() {
        return produt_img_path;
    }

    public void setProdut_img_path(String produt_img_path) {
        this.produt_img_path = produt_img_path;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }
}
