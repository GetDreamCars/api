# Car Advert Application

This is a Spring Boot application for managing car adverts, including functionality to create, update, delete, and view car advertisements. The app also integrates with a cloud service (e.g., Cloudinary) to upload and store images associated with each advert.

## Features

- CRUD operations for car adverts
- Image uploading with Cloudinary integration
- H2 in-memory database for local development
- RESTful API endpoints

## Technologies

- **Java 17**
- **Spring Boot**
- **Maven**
- **H2 Database** (in-memory)
- **Cloudinary** (for image storage)
- **IntelliJ IDEA** (recommended IDE)

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- Cloudinary account (optional, for image uploading)

### Setup

1. Clone the repository
2. Build the project with Maven:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. Access the application at [http://localhost:8080](http://localhost:8080).

### H2 Database Console

The H2 database console is available at [http://localhost:8080/h2-console](http://localhost:8080/h2-console) with the following settings:

- **JDBC URL**: `jdbc:h2:mem:car_adverts_db`
- **Username**: `sa`
- **Password**: `password`

## Configuration

### Cloudinary

To enable Cloudinary for image storage, add the following properties to your `application.properties` or as environment variables:

```properties
cloudinary.cloud_name=your-cloud-name
cloudinary.api_key=your-api-key
cloudinary.api_secret=your-api-secret
