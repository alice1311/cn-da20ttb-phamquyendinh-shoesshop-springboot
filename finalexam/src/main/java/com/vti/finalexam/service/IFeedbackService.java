package com.vti.finalexam.service;

import com.vti.finalexam.entity.Feedback;
import com.vti.finalexam.entity.Product;
import com.vti.finalexam.form.ProductFormCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFeedbackService {
    public Page<Feedback> getAllFeedbacks(Pageable pageable, String search);
//    public void createProduct(ProductFormCreating productFormCreating);
//    public  void updateProduct(int id, ProductFormCreating productFormCreating);

    public Feedback getFeedbackById(int id);
//    public Feedback getFeedbackByIdProduct(int product_id);

    //    public boolean doesProductExist(String name, String color, String size);
    public  void deleteFeedback(int id);

    void deleteFeedbacks(List<Integer> ids);
}
