package com.humga.service;

import com.humga.entity.Authority;
import com.humga.repository.AuthorityRepository;
import com.humga.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HibernateUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;
    private final AuthorityRepository authRepo;

    public HibernateUserDetailsService(UserRepository userRepo, AuthorityRepository authRepo) {
        this.userRepo = userRepo;
        this.authRepo = authRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.humga.entity.User user = userRepo.findUserByUsername(username).orElseThrow();
        List<Authority> authorities = authRepo.findAuthorityByUser(user);
        List<GrantedAuthority> grantedAuthorities = authorities.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(),grantedAuthorities);
    }
}
