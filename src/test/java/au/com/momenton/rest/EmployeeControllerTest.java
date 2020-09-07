package au.com.momenton.rest;

import au.com.momenton.domain.Employee;
import au.com.momenton.service.EmployeesOrderingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeesOrderingService employeesOrderingService;

    private EmployeeController employeeController;

    @Before
    public void setUp(){
        employeeController = new EmployeeController(employeesOrderingService);
    }

    @Test
    public void should_retrieve_the_list_of_employees_in_the_model() {

        final Employee topEmployee1 = new Employee(130l,  "John");
        final Employee e1 = new Employee(156l, "Mark");
        final Employee e2 = new Employee(198l, "Raff");
        topEmployee1.setDirectEmployees(Arrays.asList(e1, e2));

        final Employee topEmployee2 = new Employee(980l,  "Tom");
        final Employee e3 = new Employee(356l, "Moss");
        final Employee e4 = new Employee(896l, "Gary");
        topEmployee2.setDirectEmployees(Arrays.asList(e3, e4));

        given(employeesOrderingService.getEmployeesHierarchy()).willReturn(Arrays.asList(topEmployee1, topEmployee2));

        final Model model = new ExtendedModelMap();

        assertThat(employeeController.getEmployees(model), is("employees"));

        List<Employee> employees = (List) model.asMap().get("employees");
        assertThat(employees.size(), is(2));
        assertThat(employees.contains(topEmployee1), is(true));
        assertThat(employees.contains(topEmployee2), is(true));

        assertThat((employees.get(0)).getDirectEmployees().size(), is(2));
        assertThat((employees.get(0)).getDirectEmployees().contains(e1), is(true));
        assertThat((employees.get(0)).getDirectEmployees().contains(e2), is(true));

        assertThat((employees.get(1)).getDirectEmployees().size(), is(2));
        assertThat((employees.get(1)).getDirectEmployees().contains(e3), is(true));
        assertThat((employees.get(1)).getDirectEmployees().contains(e4), is(true));

    }


}