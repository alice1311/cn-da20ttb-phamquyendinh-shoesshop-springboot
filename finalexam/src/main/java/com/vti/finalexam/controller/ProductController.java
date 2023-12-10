package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AccountDTO;
import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping(value = "api/v1/products")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProductController {
    @Autowired
    private IProductService service;
    @GetMapping()
    public ResponseEntity<?> getAllProducts(Pageable pageable, @RequestParam String search){

        Page<Product> entitiesPage = service.getAllProducts(pageable, search);
        Page<ProductDTO> dtoPage = entitiesPage.map(new Function<Product, ProductDTO>() {
            @Override
            public ProductDTO apply(Product product) {
                ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(),  product.getQuantity_stock(),product.getImage_url(),product.getColor(), product.getSize(), product.getPrice(), product.getTypeProduct().getName());
                return dto;
            }
        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductFormCreating formCreating){
        service.createProduct(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") int id, @RequestBody ProductFormCreating formCreating){
     service.updateProduct(id, formCreating);
     return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") int id){
        Product product = service.getProductById(id);
        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity_stock(),
                product.getImage_url(),
                product.getColor(),
                product.getSize(),
                product.getPrice(),
                product.getTypeProduct().getName());
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") int id){
        service.deleteProduct(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }

    @GetMapping("/checkExistence/{name}/{color}/{size}")
    public boolean checkProductExistence(
            @PathVariable String name,
            @PathVariable String color,
            @PathVariable String size
    ) {
        boolean exists = service.doesProductExist(name, color, size);
        return exists;
    }

    @DeleteMapping()
    public void deleteProducts(@RequestParam(name="ids") List<Integer> ids){
        service.deleteProducts(ids);
    }
}


