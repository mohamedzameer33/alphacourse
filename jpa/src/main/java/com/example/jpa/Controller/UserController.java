package com.example.jpa.Controller;

import com.example.jpa.Model.Student;
import com.example.jpa.Model.Userss;
import com.example.jpa.Repository.UserRepository;
import com.example.jpa.Service.StudentService;
import com.example.jpa.Service.UserssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {

    @Autowired
    StudentService service;

    @Autowired
    UserssService userService;

    @Autowired
    UserRepository userRepository;



    // Get current logged-in user's profile
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            Userss user = userRepository.getByUsername(username);

            if (user != null) {
                Map<String, Object> profile = new HashMap<>();
                profile.put("id", user.getId());
                profile.put("username", user.getUsername());
                profile.put("role", user.getRole());
                return ResponseEntity.ok(profile);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching profile");
        }
    }

    @GetMapping("/student/{username}")
    public ResponseEntity<?> getStudentByUsername(@PathVariable String username) {
        try {
            Student student = service.getStudentByUsername(username);

            if (student != null) {
                return ResponseEntity.ok(student);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching student data");
        }
    }


}