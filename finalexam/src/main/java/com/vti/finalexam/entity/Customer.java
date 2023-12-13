package com.vti.finalexam.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Account{
    @Getter
    @OneToMany(mappedBy = "customer")
    private List<Order> orders_buy;
    @OneToMany(mappedBy = "account_customer")
    private List<Feedback> feedbacks;

    public Customer(String username, String password, String firstName, String lastName, String email, Role role, List<Order> orders_buy, List<Feedback> feedbacks) {
        super(username, password, firstName, lastName, email, role);
        this.orders_buy = orders_buy;
        this.feedbacks = feedbacks;
    }

    public Customer(List<Order> orders_buy, List<Feedback> feedbacks) {
        this.orders_buy = orders_buy;
        this.feedbacks = feedbacks;
    }

    public void setOrders_buy(List<Order> orders_buy) {
        this.orders_buy = orders_buy;
    }

//    @Override
//    public List<Feedback> getFeedbacks() {
//        return feedbacks;
//    }
//
//    @Override
//    public void setFeedbacks(List<Feedback> feedbacks) {
//        this.feedbacks = feedbacks;
//    }

    public Customer() {
    }
}
