<<<<<<< HEAD
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
=======
package com.vti.finalexam.entity;public class Admin {
>>>>>>> origin/master
}
