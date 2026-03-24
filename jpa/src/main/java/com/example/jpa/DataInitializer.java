package com.example.jpa;

import com.example.jpa.Model.Userss;
import com.example.jpa.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    @Value("${ADMIN_PASSWORD}")
    private String adminPass;
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); // If you use security

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            Userss admin = new Userss();
            admin.setUsername("superadmin");
            admin.setPassword(passwordEncoder.encode(adminPass));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }
}