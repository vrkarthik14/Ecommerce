package com.example.codex_pc.ecommerce;

public class cart {

    Product product;
    String user_chhosen_qty;

    public cart() {
    }

    public cart(Product product, String user_chhosen_qty) {
        this.product = product;
        this.user_chhosen_qty = user_chhosen_qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUser_chhosen_qty() {
        return user_chhosen_qty;
    }

    public void setUser_chhosen_qty(String user_chhosen_qty) {
        this.user_chhosen_qty = user_chhosen_qty;
    }
}
