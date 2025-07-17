package com.school.lms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Teacher")
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String employeeId;

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference  // Allows serialization of subjects list
    private List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @JsonIgnore  // Prevents recursion with Classroom
    private Classroom classroom;
}
