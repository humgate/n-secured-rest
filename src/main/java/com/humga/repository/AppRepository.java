package com.humga.repository;

import com.humga.entity.Customer;
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
}
