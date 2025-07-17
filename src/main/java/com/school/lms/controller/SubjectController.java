package com.school.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.school.lms.entity.Subject;
import com.school.lms.service.SubjectService;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "http://localhost:4200")
public class SubjectController {

    @Autowired
    private SubjectService subjServ;

    // Get all subjects
    @GetMapping("/all")
    public List<Subject> getAllSubjects() {
        return subjServ.getAllSubject();
    }

    // Get subject by id
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id) {
        return subjServ.getSubjectById(id);
    }

    // Add a new subject
    @PostMapping("/add")
    public Subject addSubject(@RequestBody Subject subject) {
        return subjServ.addSubject(subject);
    }

    // Update subject by id
    @PutMapping("/update/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
        return subjServ.UpdateSubject(id, updatedSubject);
    }

    // Delete subject by id
    @DeleteMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjServ.deleteSubject(id);
        return "Subject deleted successfully";
    }
}
