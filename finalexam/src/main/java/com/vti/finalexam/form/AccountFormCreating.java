package com.vti.finalexam.form;

import com.vti.finalexam.entity.Account;

public class AccountFormCreating {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Account.Role role;


    public AccountFormCreating(String username, String password, String firstName, String lastName, String email, Account.Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public AccountFormCreating() {
    }

    public String getUsername() {
        return username;
    }

    public Account.Role getRole() {
        return role;
    }

    public void setRole(Account.Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
