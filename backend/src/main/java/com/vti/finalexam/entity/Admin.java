package com.vti.finalexam.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account{
    public Admin(String username, String password, String firstName, String lastName, String address, Date birthday, String email, Role role, Gender gender, Date createdDate) {
        super(username, password, firstName, lastName, address, birthday, email, role, gender, createdDate);
    }

    public Admin(int id, String username, String password, String firstName, String lastName, String address, Date birthday, String email, Role role, Gender gender, Date createdDate) {
        super(id, username, password, firstName, lastName, address, birthday, email, role, gender, createdDate);
    }

    public Admin() {
    }
}
