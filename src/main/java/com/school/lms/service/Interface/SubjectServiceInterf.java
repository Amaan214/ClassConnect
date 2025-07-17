package com.school.lms.service.Interface;

import java.util.List;

import com.school.lms.entity.Subject;

public interface SubjectServiceInterf {

	List<Subject> getAllSubject();
	
	Subject getSubjectById(long id);
	
	Subject addSubject(Subject subject);
	
	Subject UpdateSubject(long id, Subject subject);
	
	void deleteSubject(long id);
}
