package com.vti.finalexam.service;

import com.vti.finalexam.entity.Product;
import com.vti.finalexam.entity.ProductType;
import com.vti.finalexam.form.ProductTypeFormCreating;
import com.vti.finalexam.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductTypeService implements IProductTypeService{
    @Autowired
    private IProductTypeRepository repository;
    @Override
    public List<ProductType> getAllProductTypes() {
        return repository.findAll();
    }

    @Override
    public void createProductType(ProductTypeFormCreating productTypeFormCreating) {

        ProductType productType = new ProductType(
                productTypeFormCreating.getName()
        );
        repository.save(productType);
    }

    @Override
    public void updateProductType(int id, ProductTypeFormCreating productTypeFormCreating) {
        ProductType productType = repository.getProductTypeById(id);
        productType.setName(productTypeFormCreating.getName());
        repository.save(productType);
    }

    @Override
    public ProductType getProductTypeById(int id) {
        return repository.findById(id);
    }

    @Override
    public void deleteProductType(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteProductTypes(List<Integer> ids) {
        repository.deleteByIds(ids);
    }
}
