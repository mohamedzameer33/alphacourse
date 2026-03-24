package com.example.jpa.Service;

import com.example.jpa.Model.Userss;
import com.example.jpa.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserssService {

    @Autowired
    UserRepository repo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public void add(Userss user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repo.save(user);

    }

    public List<Userss> allUsers() {
      return repo.findAll();
    }

    public void delusers(int id) {
        repo.deleteById(id);
    }
}
