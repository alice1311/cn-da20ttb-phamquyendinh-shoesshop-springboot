package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AccountDTO;
import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.DTO.ProductTypeDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.form.ProductTypeFormCreating;
import com.vti.finalexam.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productTypes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProductTypeController {
    @Autowired
    private IProductTypeService service;

    @GetMapping()
    public ResponseEntity<?> getAllProductTypes(){
        List<ProductType> entities = service.getAllProductTypes();
        List<ProductTypeDTO> dtos = new ArrayList<>();
        for (ProductType productType: entities){
            ProductTypeDTO dto = new ProductTypeDTO(
                    productType.getId(),
                    productType.getName()
            );
            dtos.add(dto);
        }
        return new ResponseEntity<List<ProductTypeDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createProductType(@RequestBody ProductTypeFormCreating formCreating){
        service.createProductType(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProductType(@PathVariable(name = "id") int id){
        service.deleteProductType(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProductType(@PathVariable(name = "id") int id, @RequestBody ProductTypeFormCreating formCreating){
        service.updateProductType(id, formCreating);
        return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @DeleteMapping()
    public void deleteProductTypes(@RequestParam(name="ids") List<Integer> ids){
        service.deleteProductTypes(ids);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductTypeById(@PathVariable(name = "id") int id){
        ProductType productType= service.getProductTypeById(id);
        ProductTypeDTO productTypeDTO = new ProductTypeDTO(
                productType.getId(),
                productType.getName()
        );
        return new ResponseEntity<ProductTypeDTO>(productTypeDTO, HttpStatus.OK);
    }
}

