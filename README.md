# User-Restful API

## Overview

The **User-Restful API** project provides a RESTful service for managing user data. This project includes endpoints for creating, retrieving, updating, and deleting user records, supporting typical CRUD operations. This document outlines the API endpoints, request and response formats, and instructions for setting up and testing the service.

## Table of Contents
- [Overview](#overview)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Service](#running-the-service)
- [API Endpoints](#api-endpoints)
  - [Create User](#create-user)
  - [Fetch All Users](#fetch-all-users)
  - [Fetch User By Id](#fetch-user-by-id)
  - [Fetch User By Name](#fetch-user-by-name)
  - [Fetch User By Email](#fetch-user-by-email)
  - [Modify User](#modify-user)
  - [Delete User](#delete-user)
- [Postman Collection](#postman-collection)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher
- PostgreSQL (or any preferred relational database)

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/user-restful-api.git
    cd user-restful-api
    ```

2. **Configure the database:**
    - Create a new PostgreSQL database.
    - Update the `application.properties` file with your database configuration:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
      spring.datasource.username=yourusername
      spring.datasource.password=yourpassword
      spring.jpa.hibernate.ddl-auto=update
      ```

3. **Build the project:**
    ```sh
    mvn clean install
    ```

### Running the Service

To start the service, run the following command:
```sh
mvn spring-boot:run
```
The service will be available at `http://localhost:7074`.

## API Endpoints

### Create User
- **Method**: POST
- **URL**: `/api/users/createUser`
- **Description**: Creates a new user.
- **Request Body**:
  ```json
  {
    "firstName": "Michael",
    "lastName": "Michaels",
    "email": "michael.michaels@example.com"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Michael",
    "lastName": "Michaels",
    "email": "michael.michaels@example.com"
  }
  ```

### Fetch All Users
- **Method**: GET
- **URL**: `/api/users/allUsers`
- **Description**: Retrieves a list of all users.
- **Response**:
  ```json
  [
    {
      "id": 1,
      "firstName": "Michael",
      "lastName": "Michaels",
      "email": "michael.michaels@example.com"
    }
  ]
  ```

### Fetch User By Id
- **Method**: GET
- **URL**: `/api/users/fetchById`
- **Query Parameter**: `id` (integer)
- **Description**: Retrieves a user by their unique identifier.
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Michael",
    "lastName": "Michaels",
    "email": "michael.michaels@example.com"
  }
  ```

### Fetch User By Name
- **Method**: GET
- **URL**: `/api/users/fetchByName`
- **Query Parameter**: `name` (string)
- **Description**: Retrieves users by their name.
- **Response**:
  ```json
  [
    {
      "id": 1,
      "firstName": "Michael",
      "lastName": "Michaels",
      "email": "michael.michaels@example.com"
    }
  ]
  ```

### Fetch User By Email
- **Method**: GET
- **URL**: `/api/users/fetchByEmail`
- **Query Parameter**: `email` (string)
- **Description**: Retrieves a user by their email address.
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Michael",
    "lastName": "Michaels",
    "email": "michael.michaels@example.com"
  }
  ```

### Modify User
- **Method**: PUT
- **URL**: `/api/users/modify`
- **Query Parameter**: `id` (integer)
- **Description**: Updates the details of an existing user identified by `id`.
- **Request Body**:
  ```json
  {
    "id": 1,
    "firstName": "Michael",
    "lastName": "Michaels",
    "email": "michael.michaels@example.com"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "firstName": "Michael",
    "lastName": "Michaels",
    "email": "michael.michaels@example.com"
  }
  ```

### Delete User
- **Method**: DELETE
- **URL**: `/api/users/delete`
- **Query Parameter**: `id` (integer)
- **Description**: Deletes a user identified by `id`.
- **Response**:
  ```json
  {
    "message": "User deleted successfully"
  }
  ```

## Postman Collection

A Postman collection named **User-Restful** is available for testing the API. It includes predefined requests for each endpoint, along with tests to validate responses.

### Importing the Collection

1. Open Postman.
2. Click on `Import`.
3. Select the `User-Restful.postman_collection.json` file.
4. Click `Import`.

### Using the Collection

- Ensure the API server is running locally on port `7074`.
- Use the predefined requests in the collection to interact with the API.
- Validate responses using the built-in tests provided in the collection.

## Testing

To run the tests included in the Postman collection:

1. Open the Postman collection.
2. Click on the collection name.
3. Click on `Run` to open the Collection Runner.
4. Select the desired environment (if any).
5. Click on `Start Test`.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for review.

### Steps to Contribute

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
