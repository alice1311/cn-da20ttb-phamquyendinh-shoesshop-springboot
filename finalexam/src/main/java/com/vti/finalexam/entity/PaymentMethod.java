<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3c57e060a8122b106e19109aa8e96b43aeb264ae
package com.vti.finalexam.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PaymentMethod")
public class PaymentMethod implements Serializable {
    @Column(name = "paymentMethodId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "paymentName", length = 100, nullable = false)
    private String name;

    @Column(name = "descriptionPayment", nullable = false, length = 200)
    private String description_payment;

    @OneToMany(mappedBy = "payment_method")
    private List<Order> orders;

    public PaymentMethod() {
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

    public String getDescription_payment() {
        return description_payment;
    }

    public void setDescription_payment(String description_payment) {
        this.description_payment = description_payment;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
<<<<<<< HEAD
=======
=======
package com.vti.finalexam.entity;public class PaymentMethod {
>>>>>>> origin/master
>>>>>>> 3c57e060a8122b106e19109aa8e96b43aeb264ae
}
