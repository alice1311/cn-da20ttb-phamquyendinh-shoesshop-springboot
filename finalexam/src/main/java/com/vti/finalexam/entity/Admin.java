<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3c57e060a8122b106e19109aa8e96b43aeb264ae
package com.vti.finalexam.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account{
    public Admin(String username, String password, String firstName, String lastName, String email, Role role) {
        super(username, password, firstName, lastName, email, role);
    }

    public Admin() {
    }
<<<<<<< HEAD
=======
=======
package com.vti.finalexam.entity;public class Admin {
>>>>>>> origin/master
>>>>>>> 3c57e060a8122b106e19109aa8e96b43aeb264ae
}
