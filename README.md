# GitPopuli
Find popular repositories from GitHub

## Description

The core of this project revolves around creating a RESTful API that interacts with GitHub's public API to fetch popular repositories based on various criteria. It includes features such as:

- Retrieving repositories by language, stars, size and date.
- Optional date filtering to fetch repositories updated since a specific date.
- Custom error handling for robust client feedback.

## Getting Started

### Dependencies

- Java 17 or higher
- Spring Boot 3.2.x
- Maven 3.x.x
- Open API
- Spring doc
- Mockito
- JUnit 5
- WebFlux
- Lombok

# Installation
To run the application locally, follow these steps:

# Clone the repository:

```bash
https://github.com/AmareeshTejVattikuti/git-populi.git
```

# Build the project:

```bash

cd git-populi
mvn clean compile
git-populi-1.0.0.jar
```

# Run the application:

```bash
java -jar target/git-populi-1.0.0.jar
```

# Alternatively, you can run the application using Maven:

```bash
mvn spring-boot:run
```
The service will start running on http://localhost:8080

# Run Tests:

```bash
mvn test
```

# Usage
Fetch popular Java repositories, sorted by stars, since date:

**Mandatory query parameters:**

Since date is mandatory. If not provided, you will get error response.

**Optional query parameters:**

**_top_** - Number of repositories to fetch. Default is 10.

**_page_** - Page number. Default is 1.

**_language_** - Language filter. If not provided, it will fetch repositories of all languages.


```bash
curl -X GET "http://localhost:8080/repositories/popular?top=100&since=2021-01-01&page=1&language=Python" -H "accept: application/json"
```


# Swagger Documentation
The application provides a Swagger UI for easy API exploration. You can access it at http://localhost:8080/webjars/swagger-ui/index.html

# API Specification

Specification file can be found under Resources - src/main/resources/api-spec.yaml

Can be accessed in Json format at - http://localhost:8080/v3/api-docs


# Features
GitHub Repository Integration: Leverages GitHub's Search API to retrieve repository data. 

Flexible Query Parameters: Allows customization of search queries using criteria like language, sort order, and update date.

Exception Handling: Implements custom exception handling for clear, actionable client-side errors.
