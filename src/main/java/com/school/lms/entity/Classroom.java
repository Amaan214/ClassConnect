package com.school.lms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Classroom")
@Builder
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String section;
	private String grade;

	@OneToMany(mappedBy = "classroom", cascade = CascadeType.REMOVE)
	@JsonIgnore
    private List<Student> students;

    // One ClassRoom has many Subjects
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Subject> subjects;
    
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Teacher> teacher;
}  