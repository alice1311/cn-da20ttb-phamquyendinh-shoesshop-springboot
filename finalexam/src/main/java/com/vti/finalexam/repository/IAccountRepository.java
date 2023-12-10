package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
   public Account findByUsername(String username);
//    public boolean existsByName(String username);
}
