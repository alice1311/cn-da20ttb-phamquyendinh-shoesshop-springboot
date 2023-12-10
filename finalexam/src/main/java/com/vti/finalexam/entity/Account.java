package com.vti.finalexam.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.TemporalType;

@Entity
@Table(name = "`Account`")
public class Account implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "`password`", length = 255, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false, updatable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false, updatable = false)
    private String lastName;

    @Column(name = "email", length = 50, nullable = false, updatable = false)
    private String email;

    @Formula(" concat(first_name, ' ', last_name)")
    private String fullName;

    @Column(name = "`role`")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;



    public enum Role {
        ADMIN, EMPLOYEE, CUSTOMER;
    }

    public Account(String username, String password, String firstName, String lastName, String email, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = Role.CUSTOMER;
        }

    }

    public Account(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
