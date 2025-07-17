package com.school.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.school.lms.entity.Subject;

import jakarta.transaction.Transactional;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	@Modifying
	@Transactional
	@Query(value = "UPDATE subject SET classroom_id = :classId WHERE id = :subjId", nativeQuery = true)
	void subjClassUpdate(Long classId, Long subjId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE subject SET teacher_id = :teachId WHERE id = :subjId", nativeQuery = true)
	void subjTeacherUpdate(Long teachId, Long subjId);
}
