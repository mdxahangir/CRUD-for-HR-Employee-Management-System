import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../../models/Employee';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  imports: [CommonModule, FormsModule, RouterModule],
  styleUrls: ['./employee-add.component.css']
})
export class EmployeeAddComponent {
  employee: Employee = {
    id: 0,
    firstname: '',
    lastname: '',
    title: '',
    division: '',
    building: '',
    room: ''
  };

  constructor(
    private employeeService: EmployeeService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.employeeService.createEmployee(this.employee).subscribe({
      next: () => this.router.navigate(['/employees']),
      error: (error) => console.error('Error creating employee:', error)
    });
  }
}