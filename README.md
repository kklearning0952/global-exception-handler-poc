# Global Exception Handler POC — Spring Boot

A hands-on Proof of Concept demonstrating centralized exception handling in a Spring Boot REST API.

## Overview

This project demonstrates how to handle application exceptions centrally and return consistent, meaningful HTTP error responses instead of exposing internal exception details to API consumers.

## Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Maven
- IntelliJ IDEA HTTP Client
- REST APIs

## Concepts Demonstrated

- Global exception handling using `@RestControllerAdvice`
- Exception-specific handlers using `@ExceptionHandler`
- Custom runtime exceptions
- HTTP status code mapping
- Consistent JSON error responses
- Handling unexpected exceptions
- Logging unexpected errors

## Project Structure

```text
com.kklearning.exceptionpoc
├── controller
│   └── PaymentController.java
├── dto
│   └── ApiError.java
├── exception
│   ├── DuplicatePaymentException.java
│   ├── GlobalExceptionHandler.java
│   └── PaymentNotFoundException.java
├── service
│   └── PaymentService.java
└── GlobalExceptionHandlerPocApplication.java
```

## API Endpoints

Base URL: `http://localhost:8080`

| HTTP Method | Endpoint | Expected Status | Description |
|---|---|---|---|
| GET | `/api/payments/1001` | 200 OK | Retrieve an existing payment |
| GET | `/api/payments/9999` | 404 Not Found | Demonstrate a custom not-found exception |
| POST | `/api/payments/1001` | 409 Conflict | Demonstrate duplicate-payment handling |
| GET | `/api/payments/test-error` | 500 Internal Server Error | Demonstrate unexpected exception handling |

The sample payment data is stored in memory for demonstration purposes. This is not a production payment-processing application.

## Sample Error Response

```json
{
  "timestamp": "2026-10-24T10:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Payment not found: 9999",
  "path": "/api/payments/9999"
}
```

The timestamp is illustrative and will vary at runtime.

## How to Run

### Prerequisites

- Java JDK compatible with the configured Spring Boot version
- IntelliJ IDEA or another Java IDE
- Git

### Start the application

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

macOS/Linux:

```bash
./mvnw spring-boot:run
```

Alternatively, run `GlobalExceptionHandlerPocApplication.java` directly from IntelliJ IDEA.

## API Testing

Open `api-tests.http` in IntelliJ IDEA and execute each request using the green Run icon.

Verify that successful requests return HTTP 200 and exception scenarios return their expected status codes.

## Key Takeaway

`@RestControllerAdvice` centralizes REST API exception handling, while `@ExceptionHandler` maps specific exception types to suitable HTTP responses.

This separation keeps controllers and service-layer business logic cleaner and makes error responses consistent.

## Future Enhancements

- Request validation using `@Valid`
- Field-level validation error responses
- Unit tests using JUnit 5 and Mockito
- Additional Spring MVC exception handlers
- RFC 9457 Problem Details support

---

**Purpose:** Learning, practice, and Java/Spring Boot interview preparation.
