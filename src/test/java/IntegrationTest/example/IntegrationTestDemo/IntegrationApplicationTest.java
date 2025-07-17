package IntegrationTest.example.IntegrationTestDemo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.lms.LmsApplication;
import com.school.lms.entity.Student;
import com.school.lms.repository.StudentRepository;


@SpringBootTest(classes = LmsApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class IntegrationApplicationTest {
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private StudentRepository studrepo;
	
	@Autowired
	private ObjectMapper objmap;
	
	Student std;
	
	/* @Container (if using JUnit 5 with Testcontainers’ @Testcontainers annotation) marks this field as a container to be managed by Testcontainers.
	 * MySQLContainer mySQLContainer = ... creates a new Docker container running the latest MySQL image.
	 * This container will be started before your tests and stopped after.*/
	
	
	/*@DynamicPropertySource is a Spring Boot annotation for dynamically setting properties before the application context starts.
	 * DynamicPropertyRegistry registry allows you to override Spring properties at runtime.
	 * This code sets the datasource URL, username, and password to the values from the running MySQL container.
	 * Your application will connect to the MySQL container instead of the default datasource.*/
	
	@BeforeEach
	public void setup() {
		studrepo.deleteAll(); // This will clear all the students before each test.
		std = Student.builder()
				.name("John")
				.email("john@gmail.com")
				.rollNumber("1C81")
				.build();
		std = studrepo.save(std);  // We are saving student information so that we can perform other operation.
	}
	
//		Get Controller
	@Test
	@Order(1)
	@WithMockUser
	public void getAllStd() throws Exception {
//		precondition
		List<Student> stdList = new ArrayList<>();
		stdList.add(std);
		stdList.add(Student.builder().name("Samuel")
				.email("amalive@yahoo.com")
				.rollNumber("1B312").build());
		studrepo.saveAll(stdList);
		
//		action
		ResultActions response = mockmvc.perform(get("/student/all"));
		
//		Verify the output
		response.andExpect(status().isOk())
		.andDo(print());
	}
	
//	get by id controller
	@Test
	@Order(2)
	@WithMockUser
	public void getStdByIdTest() throws Exception {		
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
				
//		action
		std.setRollNumber("1A201");
		ResultActions response = mockmvc.perform(put("/student/update/{id}", std.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objmap.writeValueAsString(std))
				.with(csrf())); // this is so we can overcome authentication error caused by the spring security.
		
//		verify
		response.andExpect(status().isOk())
		.andDo(print())
		;
	}
	
	@Test
	@Order(4)
	@WithMockUser
	public void delStdTest() throws Exception {
		
//		action
		ResultActions response = mockmvc.perform(delete("/student/delete/{id}", std.getId())
				.with(csrf()));
		
//		verify
		response.andExpect(status().isOk())
		.andDo(print());
	}
}
