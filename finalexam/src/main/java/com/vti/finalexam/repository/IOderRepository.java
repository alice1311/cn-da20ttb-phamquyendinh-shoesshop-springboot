package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Feedback;
import com.vti.finalexam.entity.Order;
import com.vti.finalexam.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOderRepository extends JpaRepository<Order, Integer> {
    public Order getOrderById(int id);
    public void deleteById(int id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Order WHERE id IN(:ids)")
    public void deleteByIds(@Param("ids") List<Integer> ids);

    <T> Page<Order> findAll(Specification<T> where, Pageable pageable);

}
