package com.vti.finalexam.DTO;

public class ProductDetailDTO {

    private int quantity;
    private String img_url;
    private String color;
    private String size;
    private int product_id;

    public int getQuantity() {
        return quantity;
    }

    public ProductDetailDTO(int quantity, String img_url, String color, String size, int product_id) {
        this.quantity = quantity;
        this.img_url = img_url;
        this.color = color;
        this.size = size;
        this.product_id = product_id;
    }

    public ProductDetailDTO() {
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
