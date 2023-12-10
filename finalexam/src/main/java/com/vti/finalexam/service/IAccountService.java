package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.form.AccountFormCreating;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    public List<Account> getAllAccounts();

    public Account getAccountByUsernam(String username);
    public void createAccount(AccountFormCreating accountFormCreating);

}
