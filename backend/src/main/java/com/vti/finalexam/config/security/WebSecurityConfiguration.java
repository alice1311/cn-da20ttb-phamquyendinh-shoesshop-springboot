package com.vti.finalexam.config.security;

import com.vti.finalexam.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private IAccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/customers/register").permitAll()
                .antMatchers("/api/v1/products/all").permitAll()
                .antMatchers("/api/v1/products/full").permitAll()
                .antMatchers("/api/v1/products/{id}").permitAll()
                .antMatchers("/api/v1/products/type/{id}").permitAll()
                .antMatchers("/api/v1/productTypes/full").permitAll()
                .antMatchers("/api/v1/products/productDetail/{id}").permitAll()
                .antMatchers("/api/v1/productDetails/**").permitAll()
                .antMatchers("/api/v1/products/**").permitAll()
                .antMatchers("/api/v1/products/filter").permitAll()
                .antMatchers("/api/v1/products/allsize").permitAll()
                .antMatchers("/api/v1/products/allcolor").permitAll()
                .antMatchers("/api/v1/products/update/{id}").hasAnyAuthority("ADMIN", "EMPLOYEE")
//                .antMatchers("/api/v1/accounts/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/productTypes/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .antMatchers("/api/v1/feedbacks/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/feedbacks/customer").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/customers").permitAll()
                .antMatchers("/api/v1/customers/**").permitAll()
                .antMatchers("/api/v1/paymentMethods/all").permitAll()
                .antMatchers("/api/v1/paymentMethods/**").permitAll()
                .antMatchers("/api/v1/customers/register/{id}").permitAll()
                .antMatchers("/api/v1/employees").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/employees/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/employees/update/{id}").hasAnyAuthority("EMPLOYEE")
                .antMatchers("/api/v1/sales").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/sales/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/admins").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/orders/getOrderByCustomer/{id}").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/orders/getCartByCustomer/{id}").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/orders/create").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/orders/checkCart/{id}").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/orders/createCart").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/orders/update").hasAnyAuthority("EMPLOYEE", "CUSTOMER")
//                .antMatchers("api/v1/orderItems/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers("/api/v1/orderItems/create").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/orderItems/update/{id}").hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/orderItems/delete/{id}").hasAnyAuthority("CUSTOMER")
//                .antMatchers("api/v1/productDetails/**").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .antMatchers("/api/v1/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
