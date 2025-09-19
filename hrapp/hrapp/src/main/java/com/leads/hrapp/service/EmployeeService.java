package com.leads.hrapp.service;

import com.leads.hrapp.model.Employee;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();
    Optional<Employee> getById(Long id);
//    List<Employee> searchByName(String name);
    Employee save(Employee emp);
    Employee update(Long id, Employee emp);
    void delete(Long id);
    void importFromXml(InputStream xmlInput) throws Exception;
}
