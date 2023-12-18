package com.vti.finalexam.service;

import com.vti.finalexam.entity.*;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.repository.ICustomerRepository;
import com.vti.finalexam.repository.IEmployeeRepository;
import com.vti.finalexam.repository.IOderRepository;
import com.vti.finalexam.repository.IPaymentMethodRepository;
import com.vti.finalexam.specification.OderSpecification;
import com.vti.finalexam.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private IOderRepository repository;

    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Override
    public Page<Order> getAllOrders(Pageable pageable, String search) {
        Specification<Order> where = null;
        if(!StringUtils.isEmpty(search)){
            OderSpecification searchSpecification = new OderSpecification("name","LIKE", search);
            where = Specification.where(searchSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public void customer_createOder(OrderFormCreating formCreating) {
        Customer customer = customerRepository.getCustomerById(formCreating.getCustomer_id());
        PaymentMethod paymentMethod = null;
        if(0 != formCreating.getPayment_method_id()){
            paymentMethod = paymentMethodRepository.getPaymentMethodById(formCreating.getPayment_method_id());
        }
        Date creating_date = new Date();
        Order order = new Order(
               creating_date,
                formCreating.getCustomer_id(),
                paymentMethod
        );
        repository.save(order);
    }

    @Override
    public void updateOder(int id, OrderFormCreating formUpdating) {
        Employee employee = employeeRepository.getEmployeeById(formUpdating.getEmployee_id());
        Order order = repository.getOrderById(id);
        if(0 != formUpdating.getPayment_method_id()){
            PaymentMethod    paymentMethod = paymentMethodRepository.getPaymentMethodById(formUpdating.getPayment_method_id());
            order.setPayment_method(paymentMethod);
        }
        order.setEmployee(employee);
        order.setOderStatus(formUpdating.getOderStatus());
        order.setOder_date(formUpdating.getOder_date());
        order.setTotal_amount(formUpdating.getTotal_amount());
        repository.save(order);
    }

    @Override
    public Order getOrderById(int id) {
        return repository.getOrderById(id);
    }

    @Override
    public void deleteOrder(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteOrders(List<Integer> ids) {
        repository.deleteByIds(ids);
    }
}
