package com.school.lms.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClassDetails {

	private Long id;
	private String section;
	private String grade;
	private List<StudentDetails> students;
	private List<SubjectDetails> subjects;
	private List<TeacherDetails> teachers;
}