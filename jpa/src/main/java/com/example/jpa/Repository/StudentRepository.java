package com.example.jpa.Repository;

import com.example.jpa.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
List<Student>findByTechnology(String technology);

@Query(nativeQuery = true,value = "SELECT * FROM student WHERE gender = :gender AND technology = :technology")
List<Student>findBygenderandtech(@Param("gender") String gender,@Param("technology") String technology);
}
