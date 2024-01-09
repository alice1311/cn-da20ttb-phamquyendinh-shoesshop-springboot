package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.CartDTO;
import com.vti.finalexam.DTO.OrderDTO;
import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.entity.*;
import com.vti.finalexam.form.OrderCustomerCreatForm;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.OrderItemForm;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    private IOrderService service;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProductDetailService productDetailService;

    @Autowired
    private IProductService productService;
    @GetMapping()
    public ResponseEntity<?> getAllOrders(Pageable pageable, @RequestParam String search){
        Page<Order> entitiesPage = service.getAllOrders(pageable, search);
        Page<OrderDTO> dtoPage = entitiesPage.map(new Function<Order, OrderDTO>() {
            @Override
            public OrderDTO apply(Order order) {
                if(order.getOderStatus() == Order.OderStatus.ADDED_TO_CARD){
                    OrderDTO dto = new OrderDTO(order.getId(),order.getOder_date(),order.getOderStatus(), order.getCustomer().getId());
                    return dto;
                }else if(order.getOderStatus() == Order.OderStatus.TO_PAY){
                    OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), order.getCustomer().getId(), order.getPayment_method().getId());
                    return dto;
                }else{
                    OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), order.getCustomer().getId(), order.getEmployee().getId(), order.getPayment_method().getId());
                    return dto;
                }

            }

        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @PostMapping(value = "/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderCustomerCreatForm orderCustomerCreatForm){
        service.customer_createOder(orderCustomerCreatForm);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

//    @PostMapping(value = "/createCart")
//    public ResponseEntity<?> createOrderCart(@RequestBody OrderFormCreating formCreating){
//        service.createCart(formCreating);
//        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
//    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable(name = "id") int id, @RequestBody OrderFormCreating formCreating){
        service.updateOder(id, formCreating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(name = "id") int id){
        Order order = service.getOrderById(id);
        OrderDTO orderDTO = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), order.getCustomer().getId(), order.getEmployee().getId(), order.getPayment_method().getId());
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getCartByCustomer/{id}")
    public ResponseEntity<?> getCartByCustomerId(@PathVariable(name = "id") int id){
        Customer customer = customerService.getCustomerById(id);
        List<Order> orders = service.getOrderByCustomer(id);
        int cartId = 0;
        for (Order order: orders){
            if(order.getOderStatus() == Order.OderStatus.ADDED_TO_CARD){
                cartId = order.getId();
            }
        }
        List<OrderItem> orderItems = orderItemService.getOrderItemByOrder(cartId);
        ArrayList<CartDTO> cartDTOS = new ArrayList<>();
        for (OrderItem orderItem : orderItems){
            ProductDetail productDetail = orderItem.getProduct_detail_order();
            Product product = productService.getProductById(productDetail.getProduct_detail().getId());
            CartDTO cartDTO = new CartDTO(orderItem.getId(), productDetail.getImg_url(), orderItem.getQuantity(), orderItem.getSubtotal(), product.getPrice(),productDetail.getSize(), product.getName(), product.getTypeProduct().getName(), productDetail.getColor());
            cartDTOS.add(cartDTO);
        }

        return new ResponseEntity<>(cartDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderByCustomer/{id}")
    public ResponseEntity<?> getOrderByCustomerId(@PathVariable(name = "id") int id){
        Customer customer = customerService.getCustomerById(id);
        List<Order> orders = service.getOrderByCustomer(id);
        ArrayList<OrderDTO> orderDTOS= new ArrayList<>();
        for(Order order : orders){
            if(order.getOderStatus() != Order.OderStatus.ADDED_TO_CARD) {
                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getTotal_amount(), order.getOder_date(), order.getOderStatus(), order.getCustomer().getId(), order.getPayment_method().getId());
                orderDTOS.add(orderDTO);
            }
        }
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") int id){
        service.deleteOrder(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }


    @DeleteMapping()
    public void deleteOrders(@RequestParam(name="ids") List<Integer> ids){
        service.deleteOrders(ids);
    }



}
