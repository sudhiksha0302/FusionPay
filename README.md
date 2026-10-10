# FusionPay# 💳 FusionPay - Finance Management System

A modern **Finance Management System** built using **Spring Boot Microservices**, **Spring Cloud**, **Angular**, and **MySQL**. The system enables users to purchase products on EMI, manage financial transactions, and provides an admin portal for managing users and products.

---

# 🚀 Features

### 👤 User Module
- User Registration
- User Login
- View User Profile
- Bank Account Registration
- Card Registration
- Fetch User Details
- Purchase Products

### 🛍 Product Module
- Add Product
- Update Product
- Delete Product
- View All Products
- Search Products
- Filter Products by Category
- Purchase Product
- Automatic Stock Update

### 💰 Finance Module
- Create Purchase
- EMI Calculation
- Purchase History
- Purchase Status
- Track Purchased Products

### 👨‍💼 Admin Module
- Add Admin
- View All Admins
- View Products
- View Users
- Manage Products
- Manage Users

### 🌐 API Gateway
- Single Entry Point
- Request Routing
- Service Discovery Integration

### 🔍 Eureka Server
- Service Registration
- Service Discovery

---

# 🏗 System Architecture

```
                 Angular Frontend
                        │
                        │
                API Gateway (8080)
                        │
     ┌──────────┬─────────────┬────────────┬────────────┐
     │          │             │            │
User Service Product Service Finance Service Admin Service
   8081            8082            8083           8084
     │              │               │              │
     └──────────────┴───────────────┴──────────────┘
                    Eureka Server
                         8761
```

---

# 🧰 Technologies Used

## Backend

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Validation
- Spring Cloud Eureka
- Spring Cloud Gateway
- OpenFeign
- Lombok
- Maven

---

## Frontend

- Angular
- TypeScript
- HTML5
- CSS3
- Bootstrap
- Angular Router
- Angular Forms
- HttpClient

---

## Database

- MySQL
- HeidiSQL

---

## Tools

- Eclipse IDE
- VS Code
- Git
- GitHub
- Postman

---

# 📂 Project Structure

```
FusionPay
│
├── backend
│   │
│   ├── eureka-server
│   ├── api-gateway
│   ├── admin-service
│   ├── finance-service
│   ├── product-service
│   └── user-service
│
├── frontend
│   └── finance-management
│
└── README.md
```

---

# 📦 Backend Microservices

---

# 1️⃣ Eureka Server

### Purpose

Service Registry used for registering and discovering microservices.

### Port

```
8761
```

---

# 2️⃣ API Gateway

### Purpose

Acts as the single entry point for all client requests.

### Port

```
8080
```

### Routes

```
/api/users/**
/api/products/**
/api/purchases/**
/api/admin/**
```

---

# 3️⃣ User Service

### Port

```
8081
```

### Responsibilities

- User Registration
- Login
- User Profile
- Bank Details
- Card Details

### Database

```
finance_users_db
```

### Tables

```
users
bank_details
card_details
```

### Main Controllers

```
UserController
```

### Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /users/register | Register User |
| POST | /users/login | Login |
| GET | /users/{id} | Get User |

---

# 4️⃣ Product Service

### Port

```
8082
```

### Responsibilities

- Product CRUD
- Search Products
- Purchase Product
- Call User Service
- Call Finance Service

### Database

```
finance_product_db
```

### Table

```
products
```

### Main Controller

```
ProductController
```

### Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | /api/products | Get All Products |
| GET | /api/products/{id} | Get Product |
| POST | /api/products | Add Product |
| PUT | /api/products/{id} | Update Product |
| DELETE | /api/products/{id} | Delete Product |
| GET | /api/products/search | Search Product |
| GET | /api/products/category/{category} | Products By Category |
| POST | /api/products/purchase | Purchase Product |

---

# 5️⃣ Finance Service

### Port

```
8083
```

### Responsibilities

- EMI Calculation
- Purchase Storage
- Purchase Tracking

### Database

```
finance_finance_db
```

### Table

```
purchase
```

### Main Controller

```
PurchaseController
```

### Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | /api/purchases | All Purchases |
| GET | /api/purchases/{id} | Purchase By Id |
| POST | /api/purchases | Create Purchase |
| PUT | /api/purchases/{id} | Update Purchase |
| DELETE | /api/purchases/{id} | Delete Purchase |

---

# 6️⃣ Admin Service

### Port

```
8084
```

### Responsibilities

- Manage Admins
- View Users
- View Products

### Database

```
finance_admin_db
```

### Table

```
admins
```

### Main Controller

```
AdminController
```

### Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /admin/save | Add Admin |
| GET | /admin/all | View Admins |
| GET | /admin/{id} | Get Admin |
| DELETE | /admin/{id} | Delete Admin |
| GET | /admin/users | View Users |
| GET | /admin/products | View Products |

---

# 🔄 Microservice Communication

FusionPay uses **OpenFeign** for inter-service communication.

### Product Service

Communicates with:

✔ User Service

✔ Finance Service

### Admin Service

Communicates with:

✔ User Service

✔ Product Service

---

# 🔗 Feign Clients

```
UserServiceClient

FinanceServiceClient

ProductServiceClient
```

---

# 📁 DTOs

The project uses DTOs for secure communication.

Examples:

```
ProductRequestDTO

ProductResponseDTO

PurchaseDTO

PurchaseRequestDTO

UserDTO

UserResponseDTO

RegisterRequestDTO

LoginRequestDTO

AdminDTO
```

---

# 📂 Packages Used

Each microservice follows a layered architecture.

```
controller

service

service.impl

repository

entity

dto

exception

config

feign

model

enums
```

---

# 🗄 Databases

## finance_users_db

Tables

```
users

bank_details

card_details
```

---

## finance_product_db

Tables

```
products
```

---

## finance_finance_db

Tables

```
purchase
```

---

## finance_admin_db

Tables

```
admins
```

---

# 🎨 Frontend Structure

```
app
│
├── core
│
│── models
│── services
│── guards
│── interceptors
│
├── shared
│
│── navbar
│── footer
│── sidebar
│── loader
│── not-found
│
├── pages
│
│── home
│── login
│── register
│── dashboard
│── profile
│── products
│── product-details
│── payment
│── transactions
│── admin-dashboard
│── manage-users
│── manage-products
│── activate-users
```

---

# 🎯 Frontend Features

- Responsive Design
- Modern Dashboard
- Product Catalog
- User Authentication
- Product Purchase
- Admin Dashboard
- User Profile
- Transaction History
- Search Products
- Category Filtering
- EMI Details

---

# 📡 API Testing

All REST APIs were tested using:

- Postman

---

# ▶ Running the Project

## Start Backend Services

Run in the following order:

```
1. Eureka Server

2. API Gateway

3. User Service

4. Product Service

5. Finance Service

6. Admin Service
```

---

## Start Angular

```
cd frontend/finance-management

npm install

ng serve
```

Application:

```
http://localhost:4200
```

---

# 🔮 Future Enhancements

- Spring Security
- JWT Authentication
- Role Based Authorization
- Resilience4j Circuit Breaker
- Global Exception Handling
- Docker
- Kubernetes
- Email Notifications
- Payment Gateway Integration
- CI/CD Pipeline
- Unit & Integration Testing
- Cloud Deployment (AWS/Azure)

---

Spring Boot | Microservices | Angular | MySQL

---

# ⭐ Project Status

✅ Microservices Completed

✅ API Gateway Integrated

✅ Eureka Service Discovery

✅ OpenFeign Communication

✅ CRUD Operations

✅ Angular Frontend

🚀 FusionPay is being developed as a complete enterprise-grade Finance Management System using modern Java Full Stack technologies.
