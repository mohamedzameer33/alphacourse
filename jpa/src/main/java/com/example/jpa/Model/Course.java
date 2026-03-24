package com.example.jpa.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;        // e.g., "Java", "React"

    private String type;        // e.g., "Backend", "Frontend"

    private int durationWeeks;  // e.g., 12

    private double price;       // e.g., 1400.00
}