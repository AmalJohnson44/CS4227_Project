

package com.amal.auth.config;

import com.amal.auth.domain.User;
import com.amal.auth.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StartupSeeder {

    @Bean
    CommandLineRunner seedUsers(UserRepo users, PasswordEncoder encoder) {
        return args -> {
            upsert(users, encoder, "admin1",  "pass", "ADMIN");
            upsert(users, encoder, "doctor1", "pass", "DOCTOR");
            upsert(users, encoder, "patient1","pass", "PATIENT");
        };
    }

    private void upsert(UserRepo users, PasswordEncoder encoder, String username, String rawPass, String role) {
        var u = users.findByUsername(username).orElseGet(User::new);
        u.setUsername(username);
        // if password does not match, regenerate with the same encoder used at runtime
        if (u.getPassword() == null || !encoder.matches(rawPass, u.getPassword())) {
            u.setPassword(encoder.encode(rawPass));
        }
        u.setRole(role);
        users.save(u);
    }
}

