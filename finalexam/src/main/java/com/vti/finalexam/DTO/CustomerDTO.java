package com.vti.finalexam.DTO;

import com.vti.finalexam.entity.Account;

import java.util.Date;

public class CustomerDTO {
    private int id;
    private String username;
    private String address;
    private Date birthday;
    private String email;
    private String fullName;
    private Account.Gender gender;
    private Date createdDate;

    public CustomerDTO(int id, String username, String address, Date birthday, String email, String fullName, Account.Gender gender, Date createdDate) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Account.Gender getGender() {
        return gender;
    }

    public void setGender(Account.Gender gender) {
        this.gender = gender;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
