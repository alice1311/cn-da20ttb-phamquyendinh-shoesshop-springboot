package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AccountDTO;
import com.vti.finalexam.DTO.ProductDTO;
import com.vti.finalexam.DTO.ProductDetailDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductDetail;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.AccountFormCreating;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.service.IProductDetailService;
import com.vti.finalexam.service.IProductService;
import com.vti.finalexam.service.IProductTypeService;
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
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private IProductService service;

    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private IProductTypeService productTypeService;

    @GetMapping(value = "/full")
    public ResponseEntity<?> getProductfull(){
        List<Product> products = service.getFullProduct();
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            if(product.getSale() == null){
                ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getImage_url(),product.getPrice(),product.getTypeProduct().getName(), product.getGender_type().toString());
                productDTOS.add(dto);
            }else{
                ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getImage_url(),product.getPrice(),product.getTypeProduct().getName(),product.getSale().getPercent_sale(), product.getGender_type().toString());
                productDTOS.add(dto);
            }
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/type/{id}")
    public ResponseEntity<?> getProductByType(@PathVariable(name = "id") int id){
        ProductType productType = productTypeService.getProductTypeById(id);
        List<Product> products = productType.getProducts();
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            if(product.getSale() == null){
                ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getImage_url(),product.getPrice(),product.getTypeProduct().getName(), product.getGender_type().toString());
                productDTOS.add(dto);
            }else{
                ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getImage_url(),product.getPrice(),product.getTypeProduct().getName(),product.getSale().getPercent_sale(), product.getGender_type().toString());
                productDTOS.add(dto);
            }
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllProducts(Pageable pageable, @RequestParam String search){
        Page<Product> entitiesPage = service.getAllProducts(pageable, search);
        Page<ProductDTO> dtoPage = entitiesPage.map(new Function<Product, ProductDTO>() {
            @Override
            public ProductDTO apply(Product product) {
                if(product.getSale() == null){
                    ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getImage_url(),product.getPrice(),product.getTypeProduct().getName(), product.getGender_type().toString());
                    return dto;
                }else{
                    ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getImage_url(),product.getPrice(),product.getTypeProduct().getName(),product.getSale().getPercent_sale(), product.getGender_type().toString());
                    return dto;
                }


            }
        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductFormCreating formCreating){
        service.createProduct(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") int id, @RequestBody ProductFormCreating formCreating){
     service.updateProduct(id, formCreating);
     return new ResponseEntity<String>("Update successfull!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") int id){
        Product product = service.getProductById(id);
        if(product.getSale() == null){
            ProductDTO productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getImage_url(),
                    product.getPrice(),
                    product.getTypeProduct().getName(),
                    product.getTypeProduct().getId(),
                    product.getGender_type().toString());
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
        }else {
            ProductDTO productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getImage_url(),
                    product.getPrice(),
                    product.getTypeProduct().getName(),
                    product.getTypeProduct().getId(),
                    product.getSale().getPercent_sale(),
                    product.getSale().getId(),
                    product.getGender_type().toString());
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/productDetail/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable(name = "id") int id){
        Product product = service.getProductById(id);
        List<ProductDetail> productDetails = product.getProductDetails();
        ArrayList<ProductDetailDTO> productDetailDTOArrayList = new ArrayList<>();
        for(ProductDetail productDetail : productDetails){
            ProductDetailDTO dto = new ProductDetailDTO(productDetail.getId(), productDetail.getQuantity(), productDetail.getImg_url(), productDetail.getColor(), productDetail.getSize(), productDetail.getProduct_detail().getId());
            productDetailDTOArrayList.add(dto);
        }
        return new ResponseEntity<>(productDetailDTOArrayList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") int id){
        service.deleteProduct(id);
        return new ResponseEntity<String>("Delete successfull!", HttpStatus.OK);
    }

//    @GetMapping("/checkExistence/{name}/{color}/{size}")
//    public boolean checkProductExistence(
//            @PathVariable String name,
//            @PathVariable String color,
//            @PathVariable String size
//    ) {
//        boolean exists = service.doesProductExist(name, color, size);
//        return exists;
//    }

    @DeleteMapping()
    public void deleteProducts(@RequestParam(name="ids") List<Integer> ids){
        service.deleteProducts(ids);
    }
}


