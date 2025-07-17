package com.school.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.lms.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

}
