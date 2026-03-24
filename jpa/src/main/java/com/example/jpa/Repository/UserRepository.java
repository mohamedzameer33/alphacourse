package com.example.jpa.Repository;


import com.example.jpa.Model.Userss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userss,Integer> {
Userss getByUsername(String username);
}
