package com.vti.finalexam.form;

public class OrderItemFormCreating {
    private int quantity;
    private int order_id;
    private int product_detail_id;



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

    public OrderItemFormCreating(int quantity, int order_id, int product_detail_id) {
        this.quantity = quantity;
        this.order_id = order_id;
        this.product_detail_id = product_detail_id;
    }

    public OrderItemFormCreating() {
    }
}
