<<<<<<< HEAD
package com.vti.finalexam.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Account{
    @OneToMany(mappedBy = "employee")
    private List<Order> orders_check;

    public List<Order> getOrders_check() {
        return orders_check;
    }

    public void setOrders_check(List<Order> orders_check) {
        this.orders_check = orders_check;
    }

    public Employee(String username, String password, String firstName, String lastName, String email, Role role, List<Order> orders_check) {
        super(username, password, firstName, lastName, email, role);
        this.orders_check = orders_check;
    }

    public Employee(String username, String password, String firstName, String lastName, String email, Role role) {
        super(username, password, firstName, lastName, email, role);
    }

    public Employee() {
    }

    public Employee(List<Order> orders_check) {
        this.orders_check = orders_check;
    }
=======
package com.vti.finalexam.entity;public class Employee {
>>>>>>> origin/master
}
