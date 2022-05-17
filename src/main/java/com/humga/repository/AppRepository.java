package com.humga.repository;

import com.humga.entity.Customer;
import com.humga.entity.Order;
import com.humga.entity.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Repository
public class AppRepository {
    private final String QUERY_RESOURCE_FILE = "select.hql";
    private String query;

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepo;

    public AppRepository(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @PostConstruct
    public void readQueryFromResourceFile() throws IOException {
        File resource = new ClassPathResource(QUERY_RESOURCE_FILE).getFile();
        query = new String(Files.readAllBytes(resource.toPath()));
    }

    public List<String> getProduct(String name) {
        return entityManager
                .createQuery(query, String.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Customer> getAllCustomers() {
        return entityManager
                .createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

    public List<Order> getOrders(String customerName) {
        return entityManager
                .createQuery("select o from Order o join o.customer c where c.name= :name", Order.class)
                .setParameter("name", customerName)
                .getResultList();
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
