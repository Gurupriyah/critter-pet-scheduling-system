# 🐾 Critter Chronologer - Pet Scheduling System

## 📌 Overview
Critter Chronologer is a Spring Boot-based backend application designed for managing pet care services. It allows users to create pet owners, register pets, manage employees, and schedule services based on employee availability and skills.

This project was developed as part of the Udacity Java Developer Nanodegree program and extended with custom implementations.

---

## 🚀 Features
- Create and manage pet owners and pets
- Add and manage employees with specific skills
- Set employee availability
- Schedule services for pets based on availability and skills
- Retrieve schedules for employees, pets, and customers

---

## 🏗️ Architecture
The application follows a layered architecture:

- **Controller Layer** – Handles REST API requests
- **Service Layer** – Contains business logic (scheduling, availability matching)
- **Repository Layer** – Handles database operations using JPA
- **Database** – Stores data using MySQL

---

## 🛠️ Technologies Used
- Java 8+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- JUnit 5 (for testing)
- Postman (for API testing)

---

## ⚙️ Setup Instructions

### Prerequisites
- Java JDK 8+
- Maven
- MySQL

### Steps to Run
1. Clone the repository
2. Configure MySQL database and update application properties
3. Run the application:
   ```bash
   mvn spring-boot:run
