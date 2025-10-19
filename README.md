# Api-Factory

## Overview
Api-Factory is a Spring Boot application designed to manage clients and their contracts. It provides a RESTful API for creating, updating, retrieving, and deleting clients and contracts, with built-in validation and error handling. The application uses PostgreSQL as its database and includes OpenAPI documentation for easy API exploration.

---

## Prerequisites
To run this application locally, ensure you have the following installed:
- **Java 17** or higher
- **Maven** (for building the project)
- **Docker** and **Docker Compose** (for running the PostgreSQL database)

---

## How to Run Locally

1. **Clone the Repository**
   ```bash
   git clone https://github.com/SaberBENZineb/Api-Factory.git
   cd api-factory
   ```

2. **Configure the Database**  
   Update the database connection details in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5433/api-factory
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

3. **Build the Application**  
   Use Maven to build the project:
   ```bash
   mvn clean install
   ```

4. **Run the Application**  
   Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**  
   - API Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## How to Run with Docker

### Build and Run Locally
1. **Clone the Repository**
   ```bash
   git clone https://github.com/SaberBENZineb/Api-Factory.git
   cd api-factory
   ```

2. **Build and Start the Containers**  
   Use Docker Compose to build the application image and start the services:
   ```bash
   docker-compose -f docker-compose-dev.yaml up --build -d
   ```

3. **Verify the Services**  
   Check if the containers are running:
   ```bash
   docker ps
   ```

4. **Access the Application**  
   - API Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

5. **Stop the Services**  
   To stop and remove the containers, run:
   ```bash
   docker-compose down
   ```

---

### Run with Pre-Built Docker Image from Docker Hub
If you want to skip building the application locally, you can pull and run the pre-built Docker image from Docker Hub.

1. **Pull the Docker Image**
   ```bash
   docker pull saberbz/api-factory:latest
   ```

2. **Run the Application**
   Use Docker Compose to start the application and database:
   ```bash
   docker-compose -f docker-compose-prod.yaml up -d
   ```

3. **Verify the Services**
   Check if the containers are running:
   ```bash
   docker ps
   ```

4. **Access the Application**
   - API Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

5. **Stop the Services**
   To stop and remove the containers, run:
   ```bash
   docker-compose down
   ```

---

### Configuration
The application connects to the PostgreSQL database using the following environment variables (configured in `docker-compose.yaml`):
- `SPRING_DATASOURCE_URL`: `jdbc:postgresql://postgres-db:5432/api-factory`
- `SPRING_DATASOURCE_USERNAME`: `postgres`
- `SPRING_DATASOURCE_PASSWORD`: `postgres`

### Volumes
The PostgreSQL data is persisted using a Docker volume:
- **Volume Name**: `postgres_data`
- **Path**: `/var/lib/postgresql/data` (inside the container)

---

## Proof of API Functionality
The application is built with the following features to ensure its functionality:
- **Spring Boot**: Provides a robust framework for RESTful API development.
- **Spring Data JPA**: Simplifies database interactions.
- **Validation Annotations**: Ensures data integrity at the request level.
- **Global Exception Handling**: Manages errors gracefully with detailed responses.
- **Swagger/OpenAPI**: Offers interactive API documentation for testing and exploration.

You can test the API using the Swagger UI or tools like Postman. The database schema is automatically managed by Hibernate, and the application includes endpoints for managing clients and contracts.

---

## Architecture and Design

The application follows a **modular layered architecture** to ensure scalability and maintainability:

1. **Controller Layer**: Handles HTTP requests and responses.
2. **Service Layer**: Contains business logic and orchestrates data flow.
3. **Repository Layer**: Interacts with the database using Spring Data JPA.
4. **Model Layer**: Defines the data structure (e.g., `Client`, `Contract`).
5. **Mapper Layer**: Converts between entities and DTOs for clean data transfer.
6. **Exception Handling**: Centralized error handling with custom exceptions.

### Key Design Features
- **Factory Pattern**: Used for creating and updating different client types (`Person`, `Company`).
- **DTOs (Data Transfer Objects)**: Decouples the internal data structure from external API responses.
- **Soft Deletes**: Ensures data integrity by marking clients as inactive instead of permanently deleting them.

This design ensures separation of concerns, making the application easy to extend and maintain.