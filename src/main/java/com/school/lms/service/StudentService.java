package com.school.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.lms.entity.Student;
import com.school.lms.repository.StudentRepository;
import com.school.lms.service.Interface.StudentServiceInterf;

@Service
public class StudentService implements StudentServiceInterf{

	@Autowired
	private StudentRepository stdrepo;
	
	@Override
	public List<Student> getAllStudent() {
		return stdrepo.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		return stdrepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Id not found."));
	}

	@Override
	public Student addStudent(Student student) {
		return stdrepo.save(student);
	}

	@Override
	public Student updateStudent(long id, Student student) {
		Student exists = getStudentById(id);
		exists.setName(student.getName());
		exists.setEmail(student.getEmail());
		exists.setRollNumber(student.getRollNumber());
		return stdrepo.save(exists);
	}

	@Override
	public void deleteStudent(long id) {
		stdrepo.deleteById(id);	
	}

}
