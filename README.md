HR Employee Management System

A Spring Boot + Angular based Employee Management System.
This project provides a REST API to manage employees and supports importing employees from an XML file.
The Angular frontend communicates with the backend via REST endpoints (http://localhost:8080/api/employees).

✨ Features

✅ List all employees
✅ Get employee by ID
✅ Search employees by first/last name
✅ Create new employee
✅ Update existing employee
✅ Delete employee
✅ Import employees from an XML file (employee.xml)

🏗️ Project Structure

com.leads.hrapp
├── config
│   └── EmployeeDataLoader.java       # Load employee.xml on startup
├── controller
│   └── EmployeeController.java       # REST API endpoints
├── model
│   ├── Employee.java                 # Employee entity
│   └── EmployeesWrapper.java         # XML wrapper for employees
├── repository
│   └── EmployeeRepository.java       # JPA repository
├── service
│   ├── EmployeeService.java          # Service interface
│   └── impl
│       └── EmployeeServiceImpl.java # Service implementation
└── resources
    └── employee.xml                  # Initial data file (XML)

⚙️ Technologies Used

Java 17+
Spring Boot 3+
Spring Data JPA & Hibernate
Jackson XML (for XML import/export)
H2 / Oracle (database)
Angular 19 (frontend)
Lombok

▶️ Running the Application
1. Clone the repository
git clone https://github.com/mdxahangir/CRUD-for-HR-Employee-Management-System.git
cd HR-Employee-Management-System

2. Run Backend (Spring Boot)
./mvnw spring-boot:run  # For Linux/Mac
mvnw.cmd spring-boot:run # For Windows
Backend runs on: http://localhost:8080

3. Run Frontend (Angular)
cd HrappFrontend
npm install
ng serve
Frontend runs on: http://localhost:4200

📦 API Endpoints

| Method | Endpoint                   | Description                       |
| ------ | -------------------------- | --------------------------------- |
| GET    | `/api/employees`           | Get all employees                 |
| GET    | `/api/employees/{id}`      | Get employee by ID                |
| GET    | `/api/employees/search?q=` | Search by first/last name         |
| POST   | `/api/employees`           | Create a new employee             |
| PUT    | `/api/employees/{id}`      | Update an existing employee       |
| DELETE | `/api/employees/{id}`      | Delete an employee                |
| POST   | `/api/employees/import`    | Import employees from an XML file |

🛠️ Notes
The application automatically imports data from employee.xml if the database is empty.
XML import expects a well-formed XML file matching EmployeesWrapper structure.
CORS is enabled for http://localhost:4200 to allow Angular frontend communication.
