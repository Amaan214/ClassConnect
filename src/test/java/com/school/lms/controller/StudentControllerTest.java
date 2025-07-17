package com.school.lms.controller;

import static org.hamcrest.CoreMatchers.is;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.lms.config.security.JwtHelper;
import com.school.lms.entity.Student;
import com.school.lms.service.StudentService;
import org.springframework.security.test.context.support.WithMockUser;

// We use MockMvc when we want to test by simulating the http requests.

@WebMvcTest(StudentController.class)
@Import(JwtHelper.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")

public class StudentControllerTest {
	@Autowired
	private MockMvc mockmvc;
	
	@MockitoBean
	private StudentService stdServ;
	
	@Autowired
	private ObjectMapper objMap;
	
	Student std;
	
	@BeforeEach
	public void setup() {
		std = Student.builder()
				.id(1L)
				.name("Sam")
				.email("samalive@yahoo.com")
				.rollNumber("1B311")
				.build()
				;
	}
	
//	Get Controller
	@Test
	@Order(1)
	@WithMockUser
	public void getStdTest() throws Exception {
//		precondition
		List<Student> stdList = new ArrayList<>();
		stdList.add(std);
		stdList.add(Student.builder().id(2L).name("Samuel")
				.email("amalive@yahoo.com")
				.rollNumber("1B312").build());
		given(stdServ.getAllStudent()).willReturn(stdList);
		/*Tells Mockito that when stdServ.getAllStudent() is called, 
		 * it should return the stdList you just created.*/
		
//		action
		ResultActions response = mockmvc.perform(get("/student/all"));
		
//		Verify the output
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.size()", is(stdList.size())));
	}
	
//	get by id controller
	@Test
	@Order(2)
	@WithMockUser
	public void getStdByIdTest() throws Exception {
//		precondition
		given(stdServ.getStudentById(std.getId())).willReturn(std);
		
//		action
		ResultActions response = mockmvc.perform(get("/student/{id}", std.getId()));
		
//		verify
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.name", is(std.getName())))
		.andExpect(jsonPath("$.email", is(std.getEmail())))
		.andExpect(jsonPath("$.rollNumber", is(std.getRollNumber())))
		;
	}
	
	@Test
	@Order(3)
	@WithMockUser
	public void updStdTest() throws Exception {
//		Precondition
		given(stdServ.getStudentById(std.getId())).willReturn(std);
		std.setRollNumber("1A201");
		given(stdServ.updateStudent(std.getId(), std)).willReturn(std);
		
//		action
		ResultActions response = mockmvc.perform(put("/student/update/{id}", std.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMap.writeValueAsString(std))
				.with(csrf())); // this is so we can overcome authentication error caused by the spring security.
		
//		verify
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.rollNumber", is(std.getRollNumber())))
		;
	}
	
	@Test
	@Order(4)
	@WithMockUser
	public void delStdTest() throws Exception {
//		precondition
		willDoNothing().given(stdServ).deleteStudent(std.getId());
		
//		action
		ResultActions response = mockmvc.perform(delete("/student/delete/{id}", std.getId())
				.with(csrf()));
		
//		verify
		response.andExpect(status().isOk())
		.andDo(print());
	}
}
