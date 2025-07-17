package com.school.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.school.lms.entity.Teacher;
import com.school.lms.service.TeacherService;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {

	@Autowired
	private TeacherService teachServ;

	// Get all teachers
	@GetMapping("/all")
	public List<Teacher> getAllTeachers() {
		return teachServ.getAllTeacher();
	}

	// Get teacher by id
	@GetMapping("/{id}")
	public Teacher getTeacherById(@PathVariable Long id) {
		return teachServ.getTeacherById(id);
	}

	// Add a new teacher
	@PostMapping("/add")
	public Teacher addTeacher(@RequestBody Teacher teacher) {
		return teachServ.addTeacher(teacher);
	}

	// Update teacher by id
	@PutMapping("/update/{id}")
	public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher updatedTeacher) {
		return teachServ.updateTeacher(id, updatedTeacher);
	}

	// Delete teacher by id
	@DeleteMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teachServ.deleteTeacher(id);
		return "Teacher deleted successfully";
	}
}
