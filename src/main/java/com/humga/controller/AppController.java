package com.humga.controller;

import com.humga.entity.Customer;
import com.humga.entity.Order;
import com.humga.entity.User;
import com.humga.service.AppService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class AppController {
    private final AppService service;
    AppController(AppService service) {this.service =service;}

    /* @Secured - Legacy. Supports more than only role-based security, but does not support SpEL */
    @Secured("ROLE_READ")
    @GetMapping("/products/fetch-product")
    public List<String> getProduct(@RequestParam String name) {
        return service.getProduct(name);
    }

    /* @RolesAllowed - Supports more than only role-based security, but does not support SpEL */
    @RolesAllowed("ROLE_WRITE")
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {  return service.getAllCustomers(); }

    /* @PreAuthorize - Checks permissions before issuing method. Supports more than only role-based security and SpEL */
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_READ')")
    @GetMapping("/users")
    public List<User> getUsers() { return service.getUsers();}

    /* @PreAuthorize - Checks permissions before issuing method. Supports more than only role-based security and SpEL */
    @PreAuthorize("#customername == authentication.principal.username and hasRole('ROLE_READ')")
    @GetMapping("/orders")
    public List<Order> getOrders(@RequestParam String customername) { return service.getOrders(customername);
    }
}
