package com.school.lms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Subject")
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference  // Prevents recursion with Teacher
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @JsonIgnore  // Prevents recursion with Classroom
    private Classroom classroom;
}
