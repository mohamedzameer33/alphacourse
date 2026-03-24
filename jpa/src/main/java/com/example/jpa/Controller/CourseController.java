package com.example.jpa.Controller;

import com.example.jpa.Service.CourseService;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.Model.Course;
import com.example.jpa.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private CourseService courseService;




    @PostMapping("/admin/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course courseRequest, Object savedCourse) {
        // Check if course already exists to prevent duplicates
       courseService.addCourse(courseRequest);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/admin/courses/{id}")
    public ResponseEntity<?> editCourse(@PathVariable Long id, @RequestBody Course courseRequest) {

       return courseService.edit(id,courseRequest);
    }

    @DeleteMapping("/admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.del(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}