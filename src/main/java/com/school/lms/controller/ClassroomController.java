package com.school.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.lms.entity.ClassDetails;
import com.school.lms.entity.Classroom;
import com.school.lms.service.ClassroomService;

@RestController
@RequestMapping("/classroom")
@CrossOrigin(origins = "http://localhost:4200")
public class ClassroomController {

	@Autowired
	private ClassroomService classServ;
	
	@GetMapping("/all")
	public List<Classroom> getAllClass(){
		return classServ.getAllClassroom();
	}
	
	@GetMapping("/{id}")
	public Classroom getClassById(@PathVariable Long id) {
		return classServ.getClassById(id);
	}
	
	@PostMapping("/add")
	public Classroom addClass(@RequestBody Classroom newCls) {
		return classServ.addClass(newCls);
	}
	
	@PutMapping("/update/{id}")
	public Classroom updClass(@PathVariable Long id, @RequestBody Classroom updatClass) {
		return classServ.updateClass(id, updatClass);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delClass(@PathVariable Long id) {
		classServ.deleteClass(id);
		return "Class deleted successfully";
	}
	
	@GetMapping("/details/{id}")
	public ClassDetails getClassDetl(@PathVariable Long id) {
		return classServ.getClassDetails(id);
	}
}
