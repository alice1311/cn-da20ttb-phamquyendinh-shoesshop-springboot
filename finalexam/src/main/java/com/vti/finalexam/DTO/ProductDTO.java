package com.vti.finalexam.DTO;

public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private int quantity_stock;
    private String image_url;
    private String color;
    private String size;
    private float price;
    private String type_name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity_stock() {
        return quantity_stock;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public float getPrice() {
        return price;
    }

    public String getType_name() {
        return type_name;
    }

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, String description, int quantity_stock, String image_url, String color, String size, float price, String type_name) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity_stock = quantity_stock;
        this.image_url = image_url;
        this.color = color;
        this.size = size;
        this.price = price;
        this.type_name = type_name;
    }
}
