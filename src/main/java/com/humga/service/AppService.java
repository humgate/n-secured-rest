package com.humga.service;

import com.humga.entity.Customer;
import com.humga.repository.AppRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
    private final AppRepository repository;
    public AppService(AppRepository repository) {this.repository = repository;}

    public List<String> getProduct(String name) {
        return repository.getProduct(name);
    }

    public List<Customer> getAllCustomers() {
        return repository.getAllCustomers();
    }
}
