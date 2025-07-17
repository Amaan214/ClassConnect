package com.school.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.lms.entity.Subject;
import com.school.lms.repository.SubjectRepository;
import com.school.lms.service.Interface.SubjectServiceInterf;

@Service
public class SubjectService implements SubjectServiceInterf {

	@Autowired
	private SubjectRepository subjRepo;

	@Override
	public List<Subject> getAllSubject() {
		return subjRepo.findAll();
	}

	@Override
	public Subject getSubjectById(long id) {
		return subjRepo.findById(id).orElseThrow(() -> new RuntimeException("Id not found."));
	}

	@Override
	public Subject addSubject(Subject subject) {
		return subjRepo.save(subject);
	}

	@Override
	public Subject UpdateSubject(long id, Subject subject) {
		Subject exists = getSubjectById(id);
		exists.setName(subject.getName());
		exists.setCode(subject.getCode());
		return subjRepo.save(exists);
	}

	@Override
	public void deleteSubject(long id) {
		subjRepo.deleteById(id);
	}

}
