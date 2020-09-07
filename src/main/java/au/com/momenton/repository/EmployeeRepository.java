package au.com.momenton.repository;

import au.com.momenton.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    Iterable<Employee> findAll();

    List<Employee> findByManager(Employee emp);
}

