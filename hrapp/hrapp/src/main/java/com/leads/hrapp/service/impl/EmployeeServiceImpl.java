package com.leads.hrapp.service.impl;

import com.leads.hrapp.model.Employee;
import com.leads.hrapp.model.EmployeesWrapper;
import com.leads.hrapp.repository.EmployeeRepository;
import com.leads.hrapp.service.EmployeeService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    // -------------------- CRUD --------------------

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return repo.findById(id);
    }

//    @Override
//    public List<Employee> searchByName(String name) {
//        return repo.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name);
//    }

    @Override
    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    @Override
    public Employee update(Long id, Employee emp) {
        return repo.findById(id).map(existing -> {
            existing.setFirstname(emp.getFirstname());
            existing.setLastname(emp.getLastname());
            existing.setDivision(emp.getDivision());
            existing.setTitle(emp.getTitle());
            existing.setBuilding(emp.getBuilding());
            existing.setRoom(emp.getRoom());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // -------------------- XML Import --------------------

    @Override
    public void importFromXml(InputStream xmlInput) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        EmployeesWrapper wrapper = xmlMapper.readValue(xmlInput, EmployeesWrapper.class);
        if (wrapper != null && wrapper.getEmployee() != null) {
            repo.saveAll(wrapper.getEmployee());
        }
    }
}
