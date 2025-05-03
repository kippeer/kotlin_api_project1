# Product Management API

A RESTful API built with Spring Boot and Kotlin for managing product data.

## Technologies Used

- Kotlin 1.8.22
- Spring Boot 3.1.5
- Spring Data JPA
- H2 Database (for development)
- Maven
- Swagger/OpenAPI for documentation

## Features

- CRUD operations for products
- Input validation
- Comprehensive error handling
- API documentation with Swagger
- Database integration with JPA
- Consistent API response format

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository
   ```
   git clone https://github.com/yourusername/product-management-api.git
   cd product-management-api
   ```

2. Build the application
   ```
   mvn clean package
   ```

3. Run the application
   ```
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

   Alternatively, you can use the Maven Spring Boot plugin:
   ```
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`
5. The Swagger UI will be available at `http://localhost:8080/swagger-ui.html`
6. The H2 Console will be available at `http://localhost:8080/h2-console`

## API Endpoints

### Products

- `GET /api/v1/products` - Get all products
- `GET /api/v1/products/{id}` - Get a specific product by ID
- `GET /api/v1/products/search?name={name}` - Search products by name
- `POST /api/v1/products` - Create a new product
- `PUT /api/v1/products/{id}` - Update an existing product
- `DELETE /api/v1/products/{id}` - Delete a product

## Project Structure

```
src
├── main
│   ├── kotlin
│   │   └── com
│   │       └── api
│   │           └── demo
│   │               ├── config
│   │               ├── controller
│   │               ├── dto
│   │               ├── exception
│   │               ├── model
│   │               ├── repository
│   │               ├── service
│   │               └── DemoApplication.kt
│   └── resources
│       └── application.properties
└── test
    └── kotlin
        └── com
            └── api
                └── demo
                    ├── controller
                    ├── repository
                    └── service
```

## Development Process

For details on the development process and completed tasks, see [GITFLOW.md](GITFLOW.md).

## License

This project is licensed under the MIT License - see the LICENSE file for details.