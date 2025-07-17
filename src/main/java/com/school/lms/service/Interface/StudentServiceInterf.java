package com.school.lms.service.Interface;

import java.util.List;

import com.school.lms.entity.Student;

public interface StudentServiceInterf {
	List<Student> getAllStudent();

	Student getStudentById(long id);

	Student addStudent(Student student);

	Student updateStudent(long id, Student student);

	void deleteStudent(long id);

}
