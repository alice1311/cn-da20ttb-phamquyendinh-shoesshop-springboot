package com.vti.finalexam.service;

import com.vti.finalexam.entity.Order;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderService {
    public Page<Order> getAllOrders(Pageable pageable, String search);

    public void customer_createOder(OrderFormCreating formCreating, List<Integer> ids);

//    public void createCart(OrderFormCreating formCreating);

    public  void updateOder(int id, OrderFormCreating formUpdating);

    public Order getOrderById(int id);

    public  void deleteOrder(int id);


    void deleteOrders(List<Integer> ids);
}
