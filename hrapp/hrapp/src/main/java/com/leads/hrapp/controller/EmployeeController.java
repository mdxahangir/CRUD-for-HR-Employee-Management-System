package com.leads.hrapp.controller;

import com.leads.hrapp.model.Employee;
import com.leads.hrapp.model.EmployeesWrapper;
import com.leads.hrapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    // -------------------- CRUD  --------------------
//id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //name
//    @GetMapping("/search")
//    public List<Employee> search(@RequestParam("q") String q) {
//        return service.searchByName(q);
//    }



    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return service.save(emp);
    }

    //  Update

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee emp) {
        try {
            Employee updated = service.update(id, emp);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    //  Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------- XML Import --------------------

//  Import  XML file

    @PostMapping("/import")
    public ResponseEntity<?> importXml(@RequestParam("file") MultipartFile file) {
        try {
            service.importFromXml(file.getInputStream());
            return ResponseEntity.ok("Imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Import failed: " + e.getMessage());
        }
    }
    // -------------------- XML Export --------------------

    //  Export XML format
    @GetMapping
    public EmployeesWrapper all() {
        EmployeesWrapper wrapper = new EmployeesWrapper();
        wrapper.setEmployee(service.getAll());
        return wrapper;
    }

}
