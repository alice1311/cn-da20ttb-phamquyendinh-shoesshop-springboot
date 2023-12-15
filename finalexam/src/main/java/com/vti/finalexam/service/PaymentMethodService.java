package com.vti.finalexam.repository;

import com.vti.finalexam.entity.PaymentMethod;
import com.vti.finalexam.form.PaymentMethodCreating;
import com.vti.finalexam.service.IPaymentMethodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service 
public class PaymentMethodService implements IPaymentMethodService {
    @Override
    public Page<PaymentMethod> getAllPaymentMethods(Pageable pageable, String search) {
        return null;
    }

    @Override
    public PaymentMethod getPaymentMethodByUsername(String username) {
        return null;
    }

    @Override
    public void createPaymentMethod(PaymentMethodCreating formCreating) {

    }

    @Override
    public void updatePaymentMethod(int id, PaymentMethodCreating paymentMethodCreating) {

    }

    @Override
    public PaymentMethod getPaymentMethodById(int id) {
        return null;
    }

    @Override
    public void deletePaymentMethod(int id) {

    }

    @Override
    public void deletePaymentMethods(List<Integer> ids) {

    }
}
