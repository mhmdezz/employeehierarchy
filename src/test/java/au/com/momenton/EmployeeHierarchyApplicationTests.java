package au.com.momenton;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.momenton.rest.EmployeeController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeHierarchyApplicationTests {

	@Autowired
	private EmployeeController employeeController;

	@Test
	public void contextLoads() throws Exception {

		assertThat(employeeController).isNotNull();
	}

}
