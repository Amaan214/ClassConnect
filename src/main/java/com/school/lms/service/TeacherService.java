package com.school.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.lms.entity.Teacher;
import com.school.lms.repository.TeacherRepository;
import com.school.lms.service.Interface.TeacherServiceInterf;

@Service
public class TeacherService implements TeacherServiceInterf {

	@Autowired
	private TeacherRepository teachRepo;

	@Override
	public List<Teacher> getAllTeacher() {
		return teachRepo.findAll();
	}

	@Override
	public Teacher getTeacherById(long id) {
		return teachRepo.findById(id).orElseThrow(() -> new RuntimeException("Id not found."));
	}

	@Override
	public Teacher addTeacher(Teacher teacher) {
		return teachRepo.save(teacher);
	}

	@Override
	public Teacher updateTeacher(long id, Teacher teacher) {
		Teacher exist = getTeacherById(id);
		exist.setName(teacher.getName());
		exist.setEmail(teacher.getEmail());
		exist.setEmployeeId(teacher.getEmployeeId());
		return teachRepo.save(exist);
	}

	@Override
	public void deleteTeacher(long id) {
		teachRepo.deleteById(id);
	}

}
