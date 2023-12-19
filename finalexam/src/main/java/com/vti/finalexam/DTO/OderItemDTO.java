package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Order;
import com.vti.finalexam.entity.ProductDetail;

import javax.persistence.*;

public class OderItemDTO {
    private int id;
    private float sell_price;
    private float subtotal;
    private int quantity;
    private int order_id;
    private int product_detail_id;

    public float getSell_price() {
        return sell_price;
    }

    public void setSell_price(float sell_price) {
        this.sell_price = sell_price;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_detail_id() {
        return product_detail_id;
    }

    public void setProduct_detail_id(int product_detail_id) {
        this.product_detail_id = product_detail_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OderItemDTO(int id, float sell_price, float subtotal, int quantity, int order_id, int product_detail_id) {
        this.id = id;
        this.sell_price = sell_price;
        this.subtotal = subtotal;
        this.quantity = quantity;
        this.order_id = order_id;
        this.product_detail_id = product_detail_id;
    }

    public OderItemDTO() {
    }
}
