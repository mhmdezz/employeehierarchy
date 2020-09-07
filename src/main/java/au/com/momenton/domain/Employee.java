package au.com.momenton.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Employee {
    public Employee(long id, String employeeName) {
        this.id = id;
        this.employeeName = employeeName;
    }

    public Employee() {
    }

    @Id
    private long id;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy="manager", fetch = FetchType.LAZY)
    private Collection<Employee> directEmployees;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Collection<Employee> getDirectEmployees() {
        return directEmployees;
    }

    public void setDirectEmployees(Collection<Employee> directEmployees) {
        this.directEmployees = directEmployees;
    }
}
