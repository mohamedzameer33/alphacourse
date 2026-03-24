package com.example.jpa.Controller;

import com.example.jpa.Model.Course;
import com.example.jpa.Model.Student;
import com.example.jpa.Model.Userss;
import com.example.jpa.Repository.UserRepository;
import com.example.jpa.Service.CourseService;
import com.example.jpa.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/public")
public class StudentController {

    @Autowired
    StudentService service;

    @Autowired
    UserRepository repo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Userss loginDetails) {
        // 1. Fetch the user from the DB using your repository method
        Userss user = repo.getByUsername(loginDetails.getUsername());

        // 2. Logic to verify user exists and password matches the BCrypt hash
        if (user != null && encoder.matches(loginDetails.getPassword(), user.getPassword())) {

            // Success: Return a response React can use
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login Successful");
            response.put("role", user.getRole());
            response.put("username", user.getUsername());

            return ResponseEntity.ok(response);
        }

        // 3. Failure: Return 401
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
    @GetMapping("/students/{rno}")
    public ResponseEntity<Student> getStudent(@PathVariable int rno){
        Student st = service.getStd(rno);
        if (st == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(st,HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<String> addstd(@RequestBody Student student) {
        // We pass the whole object to the service now
        service.addstd(student);
        return new ResponseEntity<>("Student Registered Successfully", HttpStatus.CREATED);
    }

    @Autowired
    private CourseService courseService;

    @GetMapping("/course")
    public List<Course> getAllCourseNames() {
        return courseService.showCourse();
    }






}
