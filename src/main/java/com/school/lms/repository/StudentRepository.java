package com.school.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.school.lms.entity.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long>{

	@Modifying // This tells jpa repository that we are performing a query that will modify the database
	@Transactional // This ensures that either all changes occur or no changes at all
	@Query(value = "UPDATE student SET classroom_id = :classId WHERE id = :studentId", nativeQuery = true)
	void studentClass(Long studentId, Long classId);
}
