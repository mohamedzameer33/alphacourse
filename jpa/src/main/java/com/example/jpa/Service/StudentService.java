package com.example.jpa.Service;

import com.example.jpa.Model.Student;
import com.example.jpa.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    @Autowired
    StudentRepository studentrepo;


    public List<Student> getallStd() {
        return studentrepo.findAll();
    }


    public Student getStd(int rno) {
        return studentrepo.findById(rno).orElse(null);
    }

    public void updstd(Student std) {
        studentrepo.save(std);
    }

    public void delstd(int id) {
        studentrepo.deleteById(id);
    }

    public void clearall() {
        studentrepo.deleteAll();
    }


    public List<Student> getbytechnology(String tech) {
        return studentrepo.findByTechnology(tech);
    }

    public List<Student> getBygenderandtech(String gender, String technology) {
        return studentrepo.findBygenderandtech(gender,technology);
    }

    public void addstd(Student s) {
        studentrepo.save(s);
    }

    public void addbulk(List<Student> students) {
        studentrepo.saveAll(students);
    }


    // NEW: Get student by username (name field)
    public Student getStudentByUsername(String username) {

        List<Student> allStudents = studentrepo.findAll();
        return allStudents.stream()
                .filter(s -> s.getName().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}