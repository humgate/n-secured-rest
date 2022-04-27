package com.humga.repository;

import com.humga.entity.Authority;
import com.humga.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    List<Authority> findAuthorityByUser(User user);
}
