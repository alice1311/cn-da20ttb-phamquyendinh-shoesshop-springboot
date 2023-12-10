package com.vti.finalexam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import com.vti.finalexam.entity.ProductType;

@Entity
@Table(name = "`Product`")
public class Product implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`", length = 50, nullable = false)
    private String name;

    @Column(name = "`description`", length = 200)
    private String description;

    @Column(name = "quantity_stock", nullable = false)
    private int quantity_stock;

    @Column(name = "image_url", length = 100)
    private String image_url;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "size", length = 50)
    private String size;

    @Column(name = "price", nullable = false)
    private float price;


    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true)
    private ProductType typeProduct;
    public Product() {
    }

    public Product(String name, String description, int quantity_stock, String image_url, String color, String size, float price, ProductType typeProduct) {
        this.name = name;
        this.description = description;
        this.quantity_stock = quantity_stock;
        this.image_url = image_url;
        this.color = color;
        this.size = size;
        this.price = price;
        this.typeProduct = typeProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ProductType getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(ProductType typeProduct) {
        this.typeProduct = typeProduct;
    }
}
