package com.school.lms.service.Interface;

import java.util.List;

import com.school.lms.entity.ClassDetails;
import com.school.lms.entity.Classroom;

public interface ClassroomServiceInterf {
	List<Classroom> getAllClassroom();

	Classroom getClassById(long id);

	Classroom addClass(Classroom classroom);

	Classroom updateClass(long id, Classroom classroom);

	void deleteClass(long id);
	
	ClassDetails getClassDetails(Long id);
}
