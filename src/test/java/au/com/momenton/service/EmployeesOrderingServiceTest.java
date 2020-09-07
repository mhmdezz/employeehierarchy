package au.com.momenton.service;

import au.com.momenton.domain.Employee;
import au.com.momenton.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesOrderingServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeesOrderingService employeesOrderingService;

    @Before
    public void setUp(){
        employeesOrderingService = new EmployeesOrderingService(employeeRepository);
    }

    @Test
    public void should_retrieve_the_top_managers_with_all_direct_employees() {

        final Employee topEmployee1 = new Employee(130l,  "John");
        final Employee e1 = new Employee(156l, "Mark");
        final Employee e2 = new Employee(198l, "Raff");
        topEmployee1.setDirectEmployees(Arrays.asList(e1, e2));

        final Employee topEmployee2 = new Employee(980l,  "Tom");
        final Employee e3 = new Employee(356l, "Moss");
        final Employee e4 = new Employee(896l, "Gary");
        topEmployee2.setDirectEmployees(Arrays.asList(e3, e4));

        given(employeeRepository.findByManager(null)).willReturn(Arrays.asList(topEmployee1, topEmployee2));

        final List topEmployees = employeesOrderingService.getEmployeesHierarchy();

        assertThat(topEmployees.size(), is(2));

        assertThat(topEmployees.get(0), is(topEmployee1));
        assertThat((topEmployees.get(0)), is(topEmployee1));
        assertThat(((Employee)topEmployees.get(0)).getDirectEmployees().size(), is(2));
        assertThat(((Employee)topEmployees.get(0)).getDirectEmployees().contains(e1), is(true));
        assertThat(((Employee)topEmployees.get(0)).getDirectEmployees().contains(e2), is(true));

        assertThat((topEmployees.get(1)), is(topEmployee2));
        assertThat(((Employee)topEmployees.get(1)).getDirectEmployees().size(), is(2));
        assertThat(((Employee)topEmployees.get(1)).getDirectEmployees().contains(e3), is(true));
        assertThat(((Employee)topEmployees.get(1)).getDirectEmployees().contains(e4), is(true));

    }

}