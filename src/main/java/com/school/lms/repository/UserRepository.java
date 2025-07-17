package com.school.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.lms.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
