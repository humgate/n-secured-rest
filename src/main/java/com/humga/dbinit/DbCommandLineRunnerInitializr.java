package com.humga.dbinit;

import com.humga.entity.Authority;
import com.humga.entity.User;
import com.humga.repository.AuthorityRepository;
import com.humga.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Инициализирует пользователей в базе если таблица пользователей пуста
 *
 */
@Component
public class DbCommandLineRunnerInitializr implements CommandLineRunner {
    private final UserRepository userRepo;
    private final AuthorityRepository authRepo;
    private final PasswordEncoder encoder;

    public DbCommandLineRunnerInitializr(
            UserRepository userRepo, AuthorityRepository authRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.authRepo = authRepo;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepo.findAll().isEmpty()) {
            User user= new User("alex", encoder.encode("passAlex"),true);
            userRepo.save(user);
            Authority authority = new Authority(user, "/customers");
            authRepo.save(authority);

            user= new User("fedor", encoder.encode("passFedor"),true);
            userRepo.save(user);
        }
    }
}
