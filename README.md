# 🐾 Online Pet Adoption Platform

A Java-based backend application demonstrating OOP, JDBC, Multithreading, DAO/Service Architecture, and a simple Swing GUI to simulate an online pet adoption service.

# 🌟 Overview

This project implements a complete backend architecture for an Online Pet Adoption Platform with three user roles—Admin, Shelter, and Adopter.
It showcases enterprise-level Java concepts such as:

✔ DAO/Service pattern
✔ JDBC with PreparedStatement
✔ Multithreading (Runnable + Thread)
✔ OOP (Inheritance, Polymorphism, Abstraction, Exception Handling)
✔ Swing GUI for demo

# 👥 User Roles & Responsibilities
User Role	Responsibilities	Demonstration
Shelter	Add pets, manage adoption availability	ShelterDashboard.java handles pet listing input
Admin	Approve/reject new pet listings, manage users	Approval logic in PetService.approveListing()
Adopter	Browse pets, apply for adoption, track status	Service layer structured for future REST/API integration

# ✨ Technical Highlights (Mark Breakdown)
Category	Marks	Concepts Demonstrated	Explanation
OOP	10	Inheritance, Polymorphism, Custom Exceptions	Admin, Shelter, Adopter extend User; displayDashboard() polymorphism; EntityNotFoundException
Collections & Generics	6	Generic Interfaces, Type-Safety	EntityManagement<T> used for CRUD operations (PetService implements EntityManagement<Pet>)
Multithreading	4	Runnable, Asynchronous execution	EmailNotification runs on a separate thread during approval
Database Connectivity	13	JDBC, PreparedStatements, DAO	Secure SQL operations + try-with-resources in DAO layer


# 🏗️ Project Architecture
src/
│
├── model/        → Entities (User, Pet, Application)
├── dao/          → JDBC DAO classes (PetDAO, UserDAO)
├── service/      → Business logic (PetService, UserService)
├── gui/          → Swing UI (ShelterDashboard, etc.)
└── MainApplication.java

# Architecture Layers

Model Layer – Defines the data structure (POJOs)

DAO Layer – Direct SQL operations (CRUD)

Service Layer – Business logic, validations, multithreading

GUI Layer – Demonstration interface (Swing)

# ⚙️ Setup & Requirements

Before running this project, ensure you have:

Java JDK 8+

NetBeans IDE

MySQL Server

MySQL JDBC Connector (mysql-connector-j.jar)
Add it to:
Project → Properties → Libraries → Add JAR

# 🗄️ Database Setup

Run this SQL in MySQL Workbench/CLI:

CREATE DATABASE IF NOT EXISTS pet_adoption_db;
USE pet_adoption_db;

-- 1. USER TABLE
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- 2. PET TABLE (foreign key → User)
CREATE TABLE Pet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shelter_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    species VARCHAR(50) NOT NULL,
    breed VARCHAR(100),
    description TEXT,
    adoption_status VARCHAR(50) NOT NULL,
    photos TEXT,
    FOREIGN KEY (shelter_id) REFERENCES User(id)
);

-- REQUIRED initial test users
INSERT INTO User (id, email, password_hash, name, role) 
VALUES 
(1, 'alice@admin.com', 'hash', 'Alice Admin', 'Admin'),
(101, 'paws@shelter.com', 'hash', 'Happy Paws', 'Shelter');


# 👉 Update DBUtil.java with your MySQL credentials.

# ▶️ Running the Application

Open project in NetBeans

Go to:
Right-click Project → Properties → Run
Set Main Class to:
com.adoption.platform.MainApplication

Press F6 to run

The Shelter Dashboard GUI will open.

# 🧪 Testing Features
1️⃣ JDBC INSERT Test (GUI)

Open GUI → Enter Pet Info → Submit

Success message + Pet ID shown

Verify using:

SELECT * FROM Pet;

2️⃣ Multithreading Test

Approve listing (Admin action)

Console will show:

Thread starting instantly

2-second delay

“Notification Sent” message

3️⃣ Polymorphism Test

In MainApplication.java:

User u1 = new Admin();
User u2 = new Shelter();
User u3 = new Adopter();

u1.displayDashboard();
u2.displayDashboard();
u3.displayDashboard();


Outputs each role's specific dashboard.

# 📁 Folder Structure Preview
.
├── README.md
├── src/
│   ├── model/
│   ├── dao/
│   ├── service/
│   ├── gui/
│   └── MainApplication.java
└── lib/
    └── mysql-connector-j.jar

# 🚀 Future Enhancements

REST API integration (Spring Boot)

JWT-based authentication

React/Angular front-end

Pet image uploads

Adoption application workflow UI
