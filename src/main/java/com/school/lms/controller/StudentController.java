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

import com.school.lms.entity.Student;
import com.school.lms.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	
    @Autowired
    private StudentService stdServ;
	
    @GetMapping("/all")
    public List<Student> getAllStd() {
        return stdServ.getAllStudent();
    }
	
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return stdServ.getStudentById(id);
    }
	
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student newStudent) {
        return stdServ.addStudent(newStudent);
    }
	
    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return stdServ.updateStudent(id, updatedStudent);
    }
	
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        stdServ.deleteStudent(id);
        return "Student deleted successfully";
    }
}
