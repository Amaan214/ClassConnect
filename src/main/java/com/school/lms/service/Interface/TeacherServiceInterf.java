package com.school.lms.service.Interface;

import java.util.List;

import com.school.lms.entity.Teacher;

public interface TeacherServiceInterf {

	List<Teacher> getAllTeacher();

	Teacher getTeacherById(long id);

	Teacher addTeacher(Teacher teacher);

	Teacher updateTeacher(long id, Teacher teacher);

	void deleteTeacher(long id);
}
