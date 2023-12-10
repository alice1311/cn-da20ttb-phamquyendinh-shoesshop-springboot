package com.vti.finalexam.controller;

import com.vti.finalexam.DTO.AccountDTO;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.form.AccountFormCreating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.vti.finalexam.service.AccountService;
import com.vti.finalexam.service.IAccountService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AccountController {
    @Autowired
    private IAccountService service;

    @GetMapping()
    public ResponseEntity<?> getAllAccounts(){
        List<Account> entities = service.getAllAccounts();
        List<AccountDTO> dtos = new ArrayList<>();
        for (Account account: entities){
            AccountDTO dto = new AccountDTO(
                    account.getUsername(),
                    account.getFullName(),
                    account.getCreatedDate(),
                    account.getRole());
            dtos.add(dto);
        }
        return new ResponseEntity<List<AccountDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createAccount(@RequestBody AccountFormCreating formCreating){
        service.createAccount(formCreating);
        return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
    }

}
