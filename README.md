# Spring Boot REST API Collection

A collection of six Spring Boot REST APIs demonstrating various CRUD operations and domain-specific functionalities.

## Projects Overview

| Project | Port | Description |
|---------|------|-------------|
| [Library API](#1-library-api) | 8082 | Book management system |
| [Student API](#2-student-api) | 8081 | Student registration and information management |
| [Restaurant Menu API](#3-restaurant-menu-api) | 8083 | Restaurant menu item management |
| [E-Commerce API](#4-e-commerce-api) | 8084 | Product catalog with advanced filtering |
| [Task Management API](#5-task-management-api) | 8085 | Task tracking with priority levels |
| [User Profile API](#6-user-profile-api) | 8086 | User profile management |

## Technologies

- **Spring Boot** 4.0.2
- **Java** 17
- **Maven** Build Tool

## Running a Project

Navigate to any project directory and run:

```bash
mvnw.cmd spring-boot:run
```

Or on Unix/Linux/Mac:

```bash
./mvnw spring-boot:run
```

---

## 1. Library API

**Port:** 8082  
**Location:** `question1-library-api/`

Manages library books with search and CRUD operations.

### Key Features
- Get all books
- Search by title
- Add/delete books
- Get book by ID

### Sample Endpoints

**Get All Books**
```
GET /api/books
```
![All Books](question1-library-api/Screenshots/books.jpg)

**Get Book by ID**
```
GET /api/books/{id}
```
![Book by ID](question1-library-api/Screenshots/by%20id.jpg)

**Search by Title**
```
GET /api/books/search?title={title}
```
![Search by Title](question1-library-api/Screenshots/title.jpg)

**Add New Book**
```
POST /api/books
```
![New Book](question1-library-api/Screenshots/new%20book.jpg)

**Delete Book**
```
DELETE /api/books/{id}
```
![Delete Book](question1-library-api/Screenshots/delete%20book.jpg)

---

## 2. Student API

**Port:** 8081  
**Location:** `question2-student-api/`

Student registration system with filtering capabilities.

![Application Running](question2-student-api/Screenshots/started.jpg)

### Key Features
- Student registration
- Filter by major
- Filter by GPA
- Update student information

### Sample Endpoints

**Get All Students**
```
GET /api/students
```
![All Students](question2-student-api/Screenshots/students.jpg)

**Get Student by ID**
```
GET /api/students/{id}
```
![Student by ID](question2-student-api/Screenshots/by%20id.jpg)

**Filter by Major**
```
GET /api/students/major/{major}
```
![By Major](question2-student-api/Screenshots/by%20major.jpg)

**Register New Student**
```
POST /api/students
```
![New Student](question2-student-api/Screenshots/new%20stud.jpg)

**Update Student**
```
PUT /api/students/{id}
```
![Update Student](question2-student-api/Screenshots/update.jpg)

---

## 3. Restaurant Menu API

**Port:** 8083  
**Location:** `question3-restaurant-api/`

Restaurant menu management with category filtering and availability tracking.

![Application Running](question3-restaurant-api/Screenshots/started.jpg)

### Key Features
- Menu item management
- Filter by category
- Toggle availability
- Search by name

### Sample Endpoints

**Get All Menu Items**
```
GET /api/menu
```
![All Menu Items](question3-restaurant-api/Screenshots/menu.jpg)

**Get Item by ID**
```
GET /api/menu/{id}
```
![Menu Item by ID](question3-restaurant-api/Screenshots/by%20id.jpg)

**Filter by Category**
```
GET /api/menu/category/{category}
```
![By Category](question3-restaurant-api/Screenshots/by%20cat.jpg)

**Get Available Items**
```
GET /api/menu/available?available={true/false}
```
![Available Items](question3-restaurant-api/Screenshots/avail.jpg)

**Search by Name**
```
GET /api/menu/search?name={name}
```
![Search by Name](question3-restaurant-api/Screenshots/by%20name.jpg)

**Add Menu Item**
```
POST /api/menu
```
![Add New Item](question3-restaurant-api/Screenshots/add%20nw.jpg)

**Toggle Availability**
```
PUT /api/menu/{id}/availability
```
![Toggle Availability](question3-restaurant-api/Screenshots/ava.jpg)

**Delete Menu Item**
```
DELETE /api/menu/{id}
```
![Delete Item](question3-restaurant-api/Screenshots/delete.jpg)

---

## 4. E-Commerce API

**Port:** 8084  
**Location:** `question4-Ecommerce-api/`

Product catalog with advanced filtering, pagination, and search.

![Application Running](question4-Ecommerce-api/Screenshots/running.jpg)

### Key Features
- Product CRUD operations
- Pagination support
- Filter by category, brand, price range
- Stock management
- Keyword search

### Sample Endpoints

**Get All Products**
```
GET /api/products
```
![All Products](question4-Ecommerce-api/Screenshots/all%20products.jpg)

**Get Products with Pagination**
```
GET /api/products?page={page}&limit={limit}
```
![Products with Pagination](question4-Ecommerce-api/Screenshots/prod%20with%20pagination.jpg)

**Get Product by ID**
```
GET /api/products/{id}
```
![Product by ID](question4-Ecommerce-api/Screenshots/with%20id.jpg)

**Filter by Category**
```
GET /api/products/category/{category}
```
![By Category](question4-Ecommerce-api/Screenshots/products%20by%20category.jpg)

**Filter by Brand**
```
GET /api/products/brand/{brand}
```
![By Brand](question4-Ecommerce-api/Screenshots/by%20brand.jpg)

**Filter by Price Range**
```
GET /api/products/price-range?min={min}&max={max}
```
![Price Range](question4-Ecommerce-api/Screenshots/price%20range.jpg)

**Get In-Stock Products**
```
GET /api/products/in-stock
```
![In Stock](question4-Ecommerce-api/Screenshots/stock.jpg)

**Search Products**
```
GET /api/products/search?keyword={keyword}
```
![Search Products](question4-Ecommerce-api/Screenshots/get%20product.jpg)

**Create New Product**
```
POST /api/products
```
![New Product](question4-Ecommerce-api/Screenshots/new%20prod.jpg)

**Update Product**
```
PUT /api/products/{id}
```
![Update Product](question4-Ecommerce-api/Screenshots/update.jpg)

**Delete Product**
```
DELETE /api/products/{id}
```
![Delete Product](question4-Ecommerce-api/Screenshots/delete.jpg)

---

## 5. Task Management API

**Port:** 8085  
**Location:** `question5-taskManagement-api/`

Task tracking system with priority levels and completion status.

![Application Running](question5-taskManagement-api/Screenshots/app%20running.jpg)

### Key Features
- Task CRUD operations
- Filter by completion status
- Filter by priority (LOW, MEDIUM, HIGH)
- Mark tasks as completed

### Sample Endpoints

**Get All Tasks**
```
GET /api/tasks
```
![All Tasks](question5-taskManagement-api/Screenshots/all%20tasks.jpg)

**Get Task by ID**
```
GET /api/tasks/{id}
```
![Task by ID](question5-taskManagement-api/Screenshots/task%20by%20id.jpg)

**Filter by Completion Status**
```
GET /api/tasks/status?completed={true/false}
```
![By Status](question5-taskManagement-api/Screenshots/task%20comp.jpg)

**Filter by Priority**
```
GET /api/tasks/priority/{priority}
```
![By Priority](question5-taskManagement-api/Screenshots/priority.jpg)

**Create New Task**
```
POST /api/tasks
```
![New Task](question5-taskManagement-api/Screenshots/new.jpg)

**Update Task**
```
PUT /api/tasks/{id}
```
![Update Task](question5-taskManagement-api/Screenshots/edit.jpg)

**Mark Task as Completed**
```
PATCH /api/tasks/{id}/complete
```
![Mark Completed](question5-taskManagement-api/Screenshots/completed.jpg)

**Delete Task**
```
DELETE /api/tasks/{id}
```
![Delete Task](question5-taskManagement-api/Screenshots/delete.jpg)

---

## 6. User Profile API

**Port:** 8086  
**Location:** `question6-userProfile-api/`

User profile management with activation/deactivation capabilities.

### Key Features
- User CRUD operations
- Profile management
- User activation/deactivation

### Sample Endpoints

**Create User**
```
POST /api/users
```
![Create User](question6-userProfile-api/Screenshots/create.jpg)

**Update User**
```
PUT /api/users/{id}
```
![Update User](question6-userProfile-api/Screenshots/Update.jpg)

**Delete User**
```
DELETE /api/users/{id}
```
![Delete User](question6-userProfile-api/Screenshots/delete.jpg)

---

## Project Structure

Each project follows standard Spring Boot architecture:

```
project-name/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── model/
│   │   │       ├── service/
│   │   │       └── controller/
│   │   └── resources/
│   └── test/
├── Screenshots/
├── pom.xml
└── README.md
```

## API Testing

All endpoints have been tested using Postman. Screenshots of API responses are included above for each endpoint.

## License

Educational project collection.
