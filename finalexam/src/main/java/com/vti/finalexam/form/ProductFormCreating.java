package com.vti.finalexam.form;

public class ProductFormCreating {
    private String name;
    private String description;
    private int quantity_stock;
    private String image_url;
    private String color;
    private String size;
    private float price;
    private int type_id;

    public ProductFormCreating() {
    }

    public ProductFormCreating(String name, String description, int quantity_stock, String image_url, String color, String size, float price, int type_id) {
        this.name = name;
        this.description = description;
        this.quantity_stock = quantity_stock;
        this.image_url = image_url;
        this.color = color;
        this.size = size;
        this.price = price;
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity_stock() {
        return quantity_stock;
    }

    public void setQuantity_stock(int quantity_stock) {
        this.quantity_stock = quantity_stock;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
