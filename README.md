# 🏥 Hospital Management System (Spring Boot)

This is a **Spring Boot-based Hospital Management System** that allows hospitals to manage **Doctors, Patients, and Appointments** efficiently. It provides CRUD operations with proper exception handling, unit testing, and RESTful API endpoints.

---

## 🚀 **Project Overview**
This project implements a **Hospital Management System** using:
- **Spring Boot** for backend
- **Spring Data JPA** for database interaction
- **MySQL / PostgreSQL** as the relational database
- **Mockito & JUnit** for testing
- **Lombok** for cleaner code
- **Junit for all the services
- **Postman Collection** for API testing  

It supports **Doctor, Patient, and Appointment Management**, ensuring seamless hospital workflows.

## ✨ **Features**
✔️ **Doctor Management** (Add, Get, Update, Delete)  
✔️ **Patient Management** (Add, Get, Update, Delete)  
✔️ **Appointment Scheduling** (Create, Fetch, Update, Cancel)  
✔️ **Exception Handling** (`ResourceNotFoundException`)  
✔️ **Industry-Standard Coding Practices**  
✔️ **Unit Tests with Mockito & JUnit**  
✔️ **Postman Collection Included for API Testing**

Create Doctor Request (POST /doctors)
{
  "name": "Dr. John Doe",
  "specialization": "Cardiologist",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "hospitalName": "City Hospital",
  "address": "123 Street, New York, USA"
}
Create Patient Request (POST /patients)
{
  "name": "Alice Smith",
  "age": 45,
  "email": "alice.smith@example.com",
  "phoneNumber": "9988776655",
  "medicalHistory": "Diabetes, High Blood Pressure",
  "isAdmitted": true,
  "doctor": {
    "id": 1
  }
}


