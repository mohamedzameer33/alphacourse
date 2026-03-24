package com.example.jpa.Service;

import com.example.jpa.Model.Course;
import com.example.jpa.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    public ResponseEntity<?> edit(Long id, Course courseRequest) {
        Optional<Course> existingCourseOpt = courseRepo.findById(id);

        if (existingCourseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course existingCourse = existingCourseOpt.get();

        existingCourse.setName(courseRequest.getName());
        existingCourse.setType(courseRequest.getType());
        existingCourse.setDurationWeeks(courseRequest.getDurationWeeks());
        existingCourse.setPrice(courseRequest.getPrice());

        Course savedCourse = courseRepo.save(existingCourse);

        return ResponseEntity.ok(savedCourse);
    }

    public ResponseEntity<String> addCourse(Course courseRequest) {
        if (courseRepo.existsByName(courseRequest.getName())) {
            return ResponseEntity.badRequest().body("Course already exists!");
        }

        Course savedCourse = courseRepo.save(courseRequest);
        return ResponseEntity.ok("Course added successfully");
    }

    public void del(Long id) {
        courseRepo.deleteById(id);
    }

    public List<Course> showCourse() {
        return courseRepo.findAll();
    }
}