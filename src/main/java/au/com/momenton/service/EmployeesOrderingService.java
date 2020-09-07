package au.com.momenton.service;

import au.com.momenton.domain.Employee;
import au.com.momenton.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesOrderingService {

    private EmployeeRepository employeeRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public EmployeesOrderingService(final EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;

    }


    public List getEmployeesHierarchy(){

        logger.info("Finding top employees");

        List<Employee> topEmployees = employeeRepository.findByManager(null);

        logger.info("Top employees: " + topEmployees.stream().map(te -> te.getEmployeeName()).collect(Collectors.joining(",")));

        return topEmployees;


    }

}
