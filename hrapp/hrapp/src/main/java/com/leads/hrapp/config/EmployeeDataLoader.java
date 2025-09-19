package com.leads.hrapp.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leads.hrapp.model.EmployeesWrapper;
import com.leads.hrapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class EmployeeDataLoader implements CommandLineRunner {

    @Autowired
    private EmployeeRepository repo;

//    public EmployeeDataLoader(EmployeeRepository repo) {
//        this.repo = repo;
//    }

    @Override
    public void run(String... args) throws Exception {
        if (repo.count() == 0) {
            XmlMapper xmlMapper = new XmlMapper();
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("employee.xml")) {
                EmployeesWrapper wrapper = xmlMapper.readValue(is, EmployeesWrapper.class);
                if (wrapper != null && wrapper.getEmployee() != null) {
                    repo.saveAll(wrapper.getEmployee());
                    System.out.println("Employees imported from employee.xml");
                }
            }
        }
    }
}
