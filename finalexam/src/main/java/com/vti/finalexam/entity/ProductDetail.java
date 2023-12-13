package com.vti.finalexam.entity;

import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ProductDetail")
public class ProductDetail implements Serializable {
    @Column(name = "id_detail")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "img_url", length = 255, nullable = false)
    private String img_url;

    @Column(name = "color", length = 30, nullable = false)
    private String color;

    @Column(name = "size", length = 20, nullable = false)
    private String size;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product_detail;

    @OneToMany(mappedBy = "product_detail_order")
    private List<OrderItem> orderItems;

}
