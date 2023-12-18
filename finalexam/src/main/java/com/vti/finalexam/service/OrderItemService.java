package com.vti.finalexam.service;

import com.vti.finalexam.entity.Order;
import com.vti.finalexam.entity.OrderItem;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductDetail;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.OrderItemFormCreating;
import com.vti.finalexam.form.updating.OrderItemFormUpdating;
import com.vti.finalexam.repository.IOderItemRepository;
import com.vti.finalexam.repository.IOderRepository;
import com.vti.finalexam.repository.IProductDetailRepository;
import com.vti.finalexam.repository.IProductRepository;
import com.vti.finalexam.specification.OderItemSpecification;
import com.vti.finalexam.specification.OderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService{
    @Autowired
    private IOderItemRepository repository;

    @Autowired
    private IOderRepository oderRepository;

    @Autowired
    private IProductDetailRepository productDetailRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<OrderItem> getAllOrderItems(Pageable pageable, String search) {
        Specification<OrderItem> where = null;
        if(!StringUtils.isEmpty(search)){
            OderItemSpecification searchSpecification = new OderItemSpecification("name","LIKE", search);
            where = Specification.where(searchSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

    @Override
    public void createOderItem(OrderItemFormCreating formCreating) {
        Order order = oderRepository.getOrderById(formCreating.getOrder_id());
        ProductDetail productDetail = productDetailRepository.getDetailById(formCreating.getProduct_detail_id());
        Product product = productDetail.getProduct_detail();
        float subtotal = formCreating.getQuantity()*product.getPrice();
        OrderItem orderItem = new OrderItem(
                order,
                productDetail,
                formCreating.getQuantity(),
                product.getPrice(),
                subtotal
        );
        repository.save(orderItem);
    }

    @Override
    public void updateOderItem(int id, OrderItemFormUpdating formUpdating) {
        OrderItem orderItem = repository.getOrderItemById(id);
        orderItem.setQuantity(formUpdating.getQuantity());
        ProductDetail productDetail = orderItem.getProduct_detail_order();
        Product product = productDetail.getProduct_detail();
        float subtotal = formUpdating.getQuantity()*product.getPrice();
        orderItem.setSubtotal(subtotal);
        orderItem.setSell_price(product.getPrice());
        repository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        return repository.getOrderItemById(id);
    }

    @Override
    public void deleteOrderItem(int id) {
     repository.deleteById(id);
    }

    @Override
    public void deleteOrderItems(List<Integer> ids) {
        repository.deleteByIds(ids);
    }


}
