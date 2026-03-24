package com.example.jpa.Controller;

import com.example.jpa.Model.Student;
import com.example.jpa.Model.Userss;
import com.example.jpa.Service.StudentService;
import com.example.jpa.Service.UserssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    StudentService service;

    @Autowired
    UserssService userservice;

    @GetMapping("/users")
    public List<Userss> users(){
        return userservice.allUsers();
    }

    @PostMapping("add-user")
    public void addUser(@RequestBody Userss user){
        userservice.add(user);
    }

    @DeleteMapping("/students/{id}")
    public String delstd(@PathVariable int id) {
        service.delstd(id);
        return "deleted";
    }
    @DeleteMapping("/users/{id}")
    public String delusers(@PathVariable int id) {
        userservice.delusers(id);
        return "deleted";
    }
    @PutMapping("/students")
    public String updstd(@RequestBody Student std) {
        service.updstd(std);
        return "added";
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

    @GetMapping("/students")
    public List<Student> getallStudent(){
        return service.getallStd();
    }

    @GetMapping("/students/{rno}")
    public ResponseEntity<Student> getStudent(@PathVariable int rno){
        Student st = service.getStd(rno);
        if (st == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(st,HttpStatus.OK);
    }

    @PostMapping("/students/bulk")
    public ResponseEntity<String> addStudents(@RequestBody List<Student> students) {
        service.addbulk(students);
        return new ResponseEntity<>("All students added", HttpStatus.CREATED);
    }

    @GetMapping("/students/technology/{tech}")
    public List<Student> getbyTech(@PathVariable String tech){
        return service.getbytechnology(tech);
    }

    @PostMapping("/students/filter")
    public List<Student> getBygenderandtech(@Param("gender") String gender, @Param("technology") String technology){
        return service.getBygenderandtech(gender,technology);
    }


    @DeleteMapping("/students/clear")
    public String clear(){
        service.clearall();
        return "Cleared all";
    }

}
