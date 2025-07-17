package com.school.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.school.lms.entity.Teacher;

import jakarta.transaction.Transactional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE teacher SET classroom_id = :classId WHERE id = :subjId", nativeQuery = true)
	void subjClassUpdate(Long subjId, Long classId);
}
