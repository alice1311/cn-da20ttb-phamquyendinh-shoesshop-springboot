package com.vti.finalexam.service;

import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.ProductFormCreating;
import com.vti.finalexam.form.ProductTypeFormCreating;

import java.util.List;

public interface IProductTypeService {
    public List<ProductType> getAllProductTypes();
    public void createProductType(ProductTypeFormCreating productTypeFormCreating);

    public  void updateProductType(int id, ProductTypeFormCreating productTypeFormCreating);
    public ProductType getProductTypeById(int id);

    void deleteProductType(int id);
    void deleteProductTypes(List<Integer> ids);
}
