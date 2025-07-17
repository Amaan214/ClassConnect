package com.school.lms.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import com.school.lms.entity.Student;
import com.school.lms.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class StudentServiceTest {
	
	@Mock
/*It is used to create mock objects for the StudentRepository interface. 
 * This will simulate the behaviour of actual repository*/	
	private StudentRepository stdRepo;
	
	@InjectMocks
	/* This will inject mocked StudentRepository into an instance of StudentService
	 * This allows StudentService to use the mocked repository during testing.*/
	private StudentService stdserv;
	
	private Student std;
	
	@BeforeEach
	public void setup() {
		std = Student.builder()
				.id(1L)
				.name("John")
				.email("john@gmail.com")
				.rollNumber("1C81")
				.build();
	}
	
	@Test
	@Order(1)
	public void getAllStd() {
		Student std1 = Student.builder()
				.id(2L)
				.name("Randy")
				.email("randy@gmail.com")
				.rollNumber("1C82")
				.build();
		
//		precondition
		given(stdRepo.findAll()).willReturn(List.of(std, std1));
		
//		action
		List<Student> stdList = stdserv.getAllStudent();
		
//		verify
		System.out.println(stdList);
		Assertions.assertThat(stdList).isNotNull();
		Assertions.assertThat(stdList.size()).isGreaterThan(1);
	}
	
	@Test
	@Order(2)
	public void getStdById() {
//		precondition
		given(stdRepo.findById(1L)).willReturn(Optional.of(std));
		
//		action
		Student result = stdserv.getStudentById(std.getId());
		
//		verify
		System.out.println(result);
		Assertions.assertThat(result).isNotNull();
	}
	
	@Test
	@Order(3)
	public void updStd() {
//		precondition
		given(stdRepo.findById(std.getId())).willReturn(Optional.of(std));
		std.setName("ROrton");
		given(stdRepo.save(std)).willReturn(std);
		
//		action
		Student updatedStd = stdserv.updateStudent(std.getId(), std);
		
//		verify
		System.out.println(updatedStd);
		assertThat(updatedStd.getName()).isEqualTo("ROrton");
	}
	
	@Test
	@Order(4)
	public void delStd() {
//		precondition
		willDoNothing().given(stdRepo).deleteById(std.getId());
		
//		action
		stdserv.deleteStudent(std.getId());
		
//		verify
		verify(stdRepo, times(1)).deleteById(std.getId());
	}
}
