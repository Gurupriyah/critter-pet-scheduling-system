# Critter Chronologer Starter

This is the starter code for the Critter Chronologer project, part of the Udacity Java Developer Nanodegree. Critter Chronologer is a pet scheduling application that allows customers to schedule appointments for their pets with employees who have the required skills.

## Project Structure

The project is built using Spring Boot and uses JPA for data persistence. It includes the following main components:

- **CritterApplication.java**: The main Spring Boot application class.
- **Controllers**: Handle HTTP requests for users, pets, and schedules.
- **Services**: Business logic for managing users, pets, and schedules.
- **Repositories**: Data access layer using Spring Data JPA.
- **DTOs**: Data Transfer Objects for API communication.
- **Entities**: JPA entities representing the data model.

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- MySQL database (or H2 for testing)

## Setup

1. Clone the repository and navigate to the `starter` directory.

2. Update the database configuration in `src/main/resources/application.properties`:
   - For MySQL: Ensure you have a MySQL server running on localhost:1234 with a database named `critterdb`, username `root`, and password `1234`.
   - Alternatively, you can use H2 in-memory database by changing the datasource URL to `jdbc:h2:mem:testdb`.

3. Build the project:
   ```
   mvn clean install
   ```

## Running the Application

Start the application with:
```
mvn spring-boot:run
```

The application will run on `http://localhost:8082` (or with the configured context path).

## Testing

Run the tests with:
```
mvn test
```

The project includes functional tests in `CritterFunctionalTest.java`.

## API Endpoints

The application provides RESTful endpoints for managing customers, pets, employees, and schedules. A Postman collection (`Udacity.postman_collection.json`) is included in the resources folder to help test the APIs.

Key endpoints include:
- `POST /user/customer` - Save a customer
- `GET /user/customer` - Get all customers
- `POST /pet` - Save a pet
- `GET /pet` - Get pets by owner or all pets
- `POST /user/employee` - Save an employee
- `GET /user/employee` - Get all employees
- `POST /schedule` - Save a schedule
- `GET /schedule` - Get schedules

Note: All endpoints are relative to the server URL and context path.

## Implementation Notes

This is starter code only. You need to implement the business logic in the service classes, complete the controllers, and ensure proper data mapping between entities and DTOs. Do not include solutions in this folder as it will be used in the Udacity Classroom.
