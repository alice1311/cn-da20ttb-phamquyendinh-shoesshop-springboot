package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Feedback;
import com.vti.finalexam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer>, JpaSpecificationExecutor<Feedback> {
    public Feedback findByName(String name);
//    public boolean existsByName(String name);
    public Feedback getFeedbackById(int id);

//    public Feedback getFeedbackByIdProduct(int product_id);

//    Optional<Product> findByNameAndColorAndSize(String name, String color, String size);

    public void deleteById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM `Feedback` WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Integer> ids);
}
