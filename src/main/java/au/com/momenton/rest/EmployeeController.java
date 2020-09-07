package au.com.momenton.rest;

import au.com.momenton.domain.Employee;
import au.com.momenton.service.EmployeesOrderingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeesOrderingService employeesOrderingService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public EmployeeController(final EmployeesOrderingService employeesOrderingService){
        this.employeesOrderingService = employeesOrderingService;

    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getEmployees(Model model){

        logger.info("Get employees hierarchy");

        List<Employee> employeesHierarchy = employeesOrderingService.getEmployeesHierarchy();

        model.addAttribute("employees", employeesHierarchy);

        logger.info("Employees hierarchy is retrieved");

        return "employees";

    }

}
