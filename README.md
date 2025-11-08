# 🏢 Department Employees Manager – Spring Boot Project

## 📋 Overview

**Department Employees Manager** is a Spring Boot application that provides RESTful APIs to manage company departments and employees.  
It allows administrators to create departments, assign employees, update information, and view structured organizational data easily.

---

## 🚀 Features

- **Department Management**
  - Create, view, update, and delete departments.
  - Retrieve employees by department.

- **Employee Management**
  - Create, view, update, and delete employees.
  - Link employees to specific departments.

- **Security**
  - JWT (JSON Web Token) authentication to secure API endpoints.

- **Pagination**
  - All list endpoints support pagination.

- **API Documentation**
  - Swagger UI available for easy API exploration and testing.
- **Modern Backend Stack**
  - Built with **Spring Boot 3+**
  - Uses **Spring Data JPA** for database operations
  - Connected to **MySQL**
  - Lightweight and clean architecture with **Lombok**

---


## ⚙️ Technologies Used

| Technology | Description |
|-------------|-------------|
| **Spring Boot 3+** | Java backend framework |
| **Spring Web** | REST API support |
| **Spring Data JPA** | Database ORM |
| **MySQL** | Relational database |
| **Lombok** | Reduces boilerplate code |
| **Maven** | Build and dependency management |
| **Java 17+** | Programming language |

---

## 🧩 Database Design

### Department Table
| Column | Type | Description |
|---------|------|-------------|
| id | BIGINT | Primary key |
| name | VARCHAR | Department name |

### Employee Table
| Column | Type | Description |
|---------|------|-------------|
| employeeID | BIGINT | Primary key |
| name | VARCHAR | Employee name |
| position | VARCHAR | Job title |
| salary | INT | Mothly salary |
| department_id | BIGINT | Foreign key → Department |

---

## API Endpoint


### 🔹 Department APIs

| Method  | Endpoint                 | Description             |
|---------|--------------------------|-------------------------|
| GET     | /api/departments         | Get all departments     |
| GET     | /api/departments/pag     | Get paginated list of departments |
| GET     | /api/departments/{id}    | Get a department by ID  |
| GET     | /api/departments/{id}/employees | Get a all department employees  |
| POST    | /api/departments         | Create a new department |
| PUT     | /api/departments/{id}    | Update an existing department |
| DELETE  | /api/departments/{id}    | Delete a department     |



### 🔹 Employee APIs

| Method | Endpoint                          | Description                 |
|--------|---------------------------------|-----------------------------|
| GET    | /api/employees                   | Get all employees            |
| GET    | /api/employees/pag             | Get paginated list of employees |
| GET    | /api/employees/{id}              | Get employee by ID           |
| POST   | /api/employees                   | Create a new employee        |
| PUT    | /api/employees/{id}              | Update employee details      |
| DELETE | /api/employees/{id}              | Delete an employee           |

---
## API Documentation and Usage

### Swagger UI

Use **Swagger UI** for interactive API documentation and testing:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

### Authentication with JWT Token

Use **Postman**, **curl**, or any HTTP client to interact with the API.  
Make sure to include the JWT token in the request header for authorized endpoints:
```bash
Authorization: Bearer <your-jwt-token>
```
---
#### Employee `position` Field

The `position` field accepts only the following values (case-sensitive):

- `INTERN`
- `MANAGER`
- `SUPERVISIOR`
- `MEMBER`

Any other value will result in a validation error.
___
### 🧰 Sample cURL Commands

#### 1. Login and get JWT token

```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"admin", "password":"password123"}'
```

#### 2.  Get Departments
```bash

curl -X GET "http://localhost:8080/api/departments" \
-H "Authorization: Bearer <your-jwt-token>"
```

#### 3.  Get employees (paginated)
```bash

curl -X GET "http://localhost:8080/api/employees?page=0&size=5" \
-H "Authorization: Bearer <your-jwt-token>"
```

#### 4. Add a New Employee
```bash

curl -X POST "http://localhost:8080/api/employees" \
-H "Authorization: Bearer <your-jwt-token>" \
-H "Content-Type: application/json" \
-d '{"name": "Jane Smith", "position": "INTERN", "salary": 4200, "department_id": 2}'
```


--- 

## 🔧 Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/alharbisa24/deptManager.git
cd deptManager
```
---

## 👨‍💻 Author

**Meshari Alharbi**  
Email: alharbisa24@gmail.com   
GitHub: [https://github.com/alharbisa24](https://github.com/alharbisa24)

---

## 🪪 License

This project is licensed under the MIT License – you are free to use, modify, and distribute it.

