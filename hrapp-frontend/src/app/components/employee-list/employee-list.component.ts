
import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { Router, RouterModule } from '@angular/router';
import { Employee } from '../../models/Employee';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  imports: [CommonModule , RouterModule, FormsModule],
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  filteredEmployees: Employee[] = [];
  searchTerm: string = '';

  constructor(
    private employeeService: EmployeeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees(): void {
    this.employeeService.getAllEmployees().subscribe({
      next: (data) => {
        this.employees = data;
        this.filteredEmployees = data; 
      },
      error: (error) => console.error('Error fetching employees:', error)
    });
  }

  deleteEmployee(id: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: () => this.loadEmployees(),
        error: (error) => console.error('Error deleting employee:', error)
      });
    }
  }

  editEmployee(id: number): void {
    this.router.navigate(['/employees/edit', id]);
  }


  searchEmployees(): void {
    const term = this.searchTerm.toLowerCase().trim();
    if (!term) {
      this.filteredEmployees = this.employees;
    } else {
      this.filteredEmployees = this.employees.filter(emp =>
        emp.id.toString().includes(term) 
        // ||
        // emp.firstname.toLowerCase().includes(term) ||
        // emp.lastname.toLowerCase().includes(term)
      );
    }
  }
}
