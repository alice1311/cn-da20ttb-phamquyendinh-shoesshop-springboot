package com.vti.finalexam.service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.form.AccountFormCreating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.vti.finalexam.repository.IAccountRepository;

import java.util.List;
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository repository;
    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public Account getAccountByUsernam(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void createAccount(AccountFormCreating form) {
        //convert
        String password = new BCryptPasswordEncoder().encode((CharSequence) form.getPassword());
       Account account = new Account(form.getUsername(), password, form.getFirstName(), form.getLastName(), form.getEmail(), form.getRole());
        repository.save(account);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username);
        if (account == null){
            throw new UsernameNotFoundException(username);
        }
//        new BCryptPasswordEncoder().encode("123456");
        return new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }


}
