package com.hospitalManagement.hospitalManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Doctor-Patient Management System
 *
 * This Spring Boot application provides a REST API for managing doctors, patients,
 * and their appointments. It demonstrates CRUD operations, Hibernate ORM,
 * and database persistence with MySQL/PostgreSQL.
 *
 * Author: Vrushang Patel
 * Date: March 2025
 */

@SpringBootApplication
public class HospitalManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);
	}
}
