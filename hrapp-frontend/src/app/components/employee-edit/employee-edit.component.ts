import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../../models/Employee';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  imports: [CommonModule, FormsModule, RouterModule],
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {
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
    private route: ActivatedRoute,
    private router: Router,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(id).subscribe({
      next: (data) => this.employee = data,
      error: (error) => console.error('Error fetching employee:', error)
    });
  }

  onSubmit(): void {
    this.employeeService.updateEmployee(this.employee.id, this.employee).subscribe({
      next: () => this.router.navigate(['/employees']),
      error: (error) => console.error('Error updating employee:', error)
    });
  }
}