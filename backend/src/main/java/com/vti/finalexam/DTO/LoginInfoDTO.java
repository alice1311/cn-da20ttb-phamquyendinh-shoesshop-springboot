package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Account;

public class LoginInfoDTO {
    private int id;
    private String fullName;

    private Account.Role role;
    public int getId() {
        return id;
    }

    public Account.Role getRole() {
        return role;
    }

    public void setRole(Account.Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public LoginInfoDTO(int id, String fullName, Account.Role role) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }
}
