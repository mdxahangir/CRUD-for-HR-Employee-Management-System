package com.leads.hrapp.repository;

import com.leads.hrapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String fn, String ln);
}
