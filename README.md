# ShipperConnect - Load Management Service

This project is a Spring Boot-based service for managing freight loads in the ShipperConnect system. It allows you to perform CRUD operations (Create, Read, Update, Delete) on `Load` entities, which represent shipments being handled by shippers.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Service Architecture](#service-architecture)
- [Endpoints](#endpoints)
- [Entity Model](#entity-model)
- [Frontend Interaction](#frontend-interaction)
- [Logging](#logging)
- [Error Handling](#error-handling)

---

## Overview

ShipperConnect provides an interface for shippers to manage their freight shipments. This service enables:
- Adding new loads (shipments).
- Fetching all loads or loads filtered by shipper ID.
- Updating load details.
- Deleting loads.

The project is structured around a REST API with service-level logging, error handling, and database interaction using JPA and Hibernate.

---

## Features

- **Add Load**: Create a new shipment load.
- **View All Loads**: Retrieve a list of all shipment loads.
- **View Loads by Shipper ID**: Retrieve loads filtered by the shipper’s unique ID.
- **Update Load**: Modify the details of an existing shipment.
- **Delete Load**: Remove a load from the system.
- **Frontend Interaction**: A form-based UI to add, edit, and delete loads.

---

## Technologies Used

- **Java 17**
- **Spring Boot**
- **Kotlin**
- **Hibernate (JPA)**
- **H2 Database** (For development/testing, but can be replaced with MySQL/PostgreSQL)
- **Thymeleaf** (Frontend templating engine)
- **HTML, JavaScript** for frontend interaction
- **SLF4J with Logback** for logging

---

## Setup Instructions

### Prerequisites

- Java 17 or higher installed
- Maven 3.6+ installed
- A database (H2 is used by default, but MySQL/PostgreSQL can be configured)

### Steps

1. **Clone the repository**:
    ```bash
    git clone https://github.com/anshitmishraa/shipper-connect.git
    cd ShipperConnect
    ```

2. **Configure the database** (Optional):
   If you want to use a database other than H2, update the `application.properties` file in the `src/main/resources` directory.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

3. **Build the project**:
    ```bash
    mvn clean install
    ```

4. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

5. **Access the application**:
   Open your browser and navigate to `http://localhost:8080/`.

---

## Service Architecture

### Core Components

1. **LoadService**:
    - Provides business logic for managing loads.
    - Handles interactions with the `LoadRepository`.
    - Performs logging and error handling.

2. **LoadRepository**:
    - A Spring Data JPA repository for database operations.
    - Provides built-in CRUD methods and custom query methods like `findAllByShipperId`.

3. **LoadController**:
    - A REST controller that exposes endpoints for managing loads.
    - Connects frontend requests with backend services.

---

## Endpoints

### 1. Add a Load
**POST /loads**

Request Payload:
```json
{
  "loadingPoint": "Mumbai",
  "unloadingPoint": "Delhi",
  "productType": "Fragile",
  "truckType": "Container",
  "noOfTrucks": 2,
  "weight": 500,
  "comment": "Handle with care",
  "shipperId": "SHIP123",
  "loadDate": "2023-06-30"
}
```

### 2. Get All Loads
**GET /loads**

Response:
```json
[
  {
    "id": 1,
    "loadingPoint": "Mumbai",
    "unloadingPoint": "Delhi",
    "productType": "Fragile",
    "truckType": "Container",
    "noOfTrucks": 2,
    "weight": 500,
    "comment": "Handle with care",
    "shipperId": "SHIP123",
    "loadDate": "2023-06-30"
  }
]
```

### 3. Get Loads by Shipper ID
**GET /loads/shipper/{shipperId}**

### 4. Get Load by Load ID
**GET /loads/{loadId}**

### 5. Update a Load
**PUT /loads/{loadId}**

Request Payload:
```json
{
  "loadingPoint": "Pune",
  "unloadingPoint": "Chennai",
  "productType": "Dry",
  "truckType": "Open",
  "noOfTrucks": 1,
  "weight": 1000,
  "comment": "Urgent",
  "shipperId": "SHIP456",
  "loadDate": "2023-07-01"
}
```

### 6. Delete a Load
**DELETE /loads/{loadId}**

---

## Entity Model

The `Load` entity represents a shipment and contains the following attributes:

```kotlin
@Entity
data class Load(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        var loadingPoint: String,
        var unloadingPoint: String,
        var productType: String,
        var truckType: String,
        var noOfTrucks: Int,
        var weight: Int,
        var comment: String? = null,
        var shipperId: String,
        var loadDate: Date
)
```

---

## Frontend Interaction

The frontend allows users to:
- **Add a new load**: A form captures the details and sends a POST request.
- **Edit an existing load**: Populate an edit form using data fetched from the backend.
- **Delete a load**: Confirmation dialog before deletion.

---

## Logging

The service uses **SLF4J** with **Logback** for logging:
- Logs are written for each CRUD operation.
- Errors are logged when exceptions occur.

Example:
```kotlin
logger.info("Fetching all loads")
logger.error("Error fetching load by loadId: {}", loadId, e)
```

---

## Error Handling

Custom error handling is implemented using exceptions:
- **ServiceException**: Thrown when an error occurs during service operations.
- **NoSuchElementException**: Thrown when a load is not found.
