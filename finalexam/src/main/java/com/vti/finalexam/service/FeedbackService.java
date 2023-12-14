package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.Feedback;
import com.vti.finalexam.form.FeedbackCreating;
import com.vti.finalexam.repository.IAccountRepository;
import com.vti.finalexam.repository.IFeedbackRepository;
import com.vti.finalexam.repository.IProductTypeRepository;
import com.vti.finalexam.specification.AccountSpecification;
import com.vti.finalexam.specification.FeedbackSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FeedbackService implements IFeedbackService{
    @Autowired
    private IFeedbackRepository repository;
    @Autowired
    private IAccountService accountService;

    @Override
    public Page<Feedback> getAllFeedbacks(Pageable pageable, String search) {
        Specification<Feedback> where = null;
        if(!StringUtils.isEmpty(search)){
            FeedbackSpecification feedbackSpecification = new FeedbackSpecification("name","LIKE", search);
            where = Specification.where(feedbackSpecification);
        }
        return repository.findAll(Specification.where(where), pageable);
    }

//    @Override
   public void createFeedback(FeedbackCreating feedbackCreating) {
//        Account account = accountService.getAccountById(feedbackCreating.getCustomer_id());
////        Account account = new Account(form.getUsername(), password, form.getFirstName(), form.getLastName(), form.getAddress(), form.getRole());
////        repository.save(account);
//        Date creating_date = new Date();
//        Feedback feedback = new Feedback(feedbackCreating.getComment(),creating_date, feedbackCreating.getRating(), feedbackCreating.getCustomer_id(););

    }

    @Override
    public Feedback getFeedbackById(int id) {
        return repository.getFeedbackById(id);
    }

//    @Override
//    public Feedback getFeedbackByIdProduct(int product_id) {
//        return repository.getFeedbackByIdProduct(product_id);
//    }

    @Override
    public void deleteFeedback(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteFeedbacks(List<Integer> ids) {
        repository.deleteByIds(ids);
    }


}
