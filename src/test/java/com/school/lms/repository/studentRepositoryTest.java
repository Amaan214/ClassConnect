package com.school.lms.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.school.lms.entity.Student;

// @DataJpaTest sets up a lightweight environment specifically for testing database related components without loading entire application context
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")

public class studentRepositoryTest {
	@Autowired
	private StudentRepository stdRepo;

	@Test
	@DisplayName("Test 1: Save student test")
	@Order(1)
	@Rollback(value = false)
	public void saveStudentTest() {
//		Action
		Student std = Student.builder().name("sam").email("sam@gmail.com").rollNumber("1A101").build();

		stdRepo.save(std);

//		Verify
		System.out.println(std);
		Assertions.assertThat(std.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getStudent() {
//		Action
		Student std = stdRepo.findById(1L).get();

		// Verify
		System.out.println(std);
		Assertions.assertThat(std.getId()).isEqualTo(1L);
	}

	@Test
	@Order(3)
	public void getStudentList() {
//		Action
		List<Student> stdList = stdRepo.findAll();
		
//		Verify
		System.out.println(stdList);
		Assertions.assertThat(stdList.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void UpdtStudent() {
//		Action
		Student std = stdRepo.findById(1L).get();
		std.setName("Samuel");
		Student stdUpd = stdRepo.save(std);
		
//		Verify
		System.out.println(stdUpd);
		Assertions.assertThat(stdUpd.getName()).isEqualTo("Samuel");
	}
	public studentRepositoryTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmp() {
//		Action
		stdRepo.deleteById(1L);
		Optional<Student> std = stdRepo.findById(1L);
		
//		Verify
//		We can only use isEmpty if we are using list or optional
		Assertions.assertThat(std).isEmpty();
	}
}
