package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.OrderDTO;
import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.entity.Order;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.OrderFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.service.IOrderItemService;
import com.vti.finalexam.service.IOrderService;
import com.vti.finalexam.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping()
    public ResponseEntity<?> getAllOrders(Pageable pageable, @RequestParam String search){
        Page<Order> entitiesPage = service.getAllOrders(pageable, search);
        Page<OrderDTO> dtoPage = entitiesPage.map(new Function<Order, OrderDTO>() {
            @Override
            public OrderDTO apply(Order order) {
                if(order.getOderStatus() == Order.OderStatus.ADDED_TO_CARD){
                    OrderDTO dto = new OrderDTO(order.getId(),order.getTotal_amount(), order.getOder_date(),order.getOderStatus(), order.getCustomer().getId());
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
    public ResponseEntity<?> createOrder(@RequestBody OrderFormCreating formCreating, @RequestParam(name="ids") List<Integer> ids){
        service.customer_createOder(formCreating, ids);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

//    @PostMapping(value = "/createCart")
//    public ResponseEntity<?> createOrderCart(@RequestBody OrderFormCreating formCreating){
//        service.customer_createOderCart(formCreating);
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
