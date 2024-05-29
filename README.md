# Movie Theatre Application

![Logo](![image](https://github.com/summahto/box-office/assets/20516618/592c2cde-8aa3-4cd5-a53d-20c009b690f2))

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Architecture](#architecture)
    - [Model](#model)
    - [View](#view)
    - [Controller](#controller)
    - [Service](#service)
4. [Installation](#installation)
5. [Usage](#usage)
6. [Screenshots](#screenshots)
7. [Technologies Used](#technologies-used)
8. [Contributing](#contributing)
9. [License](#license)

## Introduction

Welcome to the Movie Theatre Application! This application is designed to manage the operations of a movie theatre, including scheduling, ticketing, and customer management. It is built using Java and follows the MVCS (Model-View-Controller-Service) architectural pattern to ensure a robust and scalable structure.

## Features

- **Movie Management:** Add, update, delete, and list movies.
- **Showtime Management:** Schedule, update, and cancel showtimes.
- **Ticket Booking:** Book, update, and cancel tickets.
- **Customer Management:** Manage customer information and preferences.
- **Reporting:** Generate reports on sales, bookings, and customer demographics.

## Architecture

The application follows the MVCS pattern:

### Model

The Model represents the data and the business logic of the application. It is responsible for retrieving data from the database, processing it, and returning it to the Controller.

### View

The View is responsible for displaying the data to the user and capturing user inputs. In this backend application, the views are represented as JSON responses for API endpoints.

### Controller

The Controller handles user input and updates the Model and View accordingly. It receives input from the user, processes it (possibly asking the Model to change its state), and returns the output display to the user.

### Service

The Service layer contains business logic and communicates between the Controller and the Model. It helps in separating business logic from the Controller.

![MVCS Diagram](path_to_mvcs_diagram_image)

## Installation

To install and run this application, follow these steps:

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/your_username/movie-theatre-application.git
    cd movie-theatre-application
    ```

2. **Set Up Database:**
   Ensure you have a MySQL database set up. Update the database configuration in `src/main/resources/application.properties`.

3. **Build the Application:**
    ```bash
    mvn clean install
    ```

4. **Run the Application:**
    ```bash
    java -jar target/movie-theatre-application.jar
    ```

## Usage

Once the application is running, you can access the API endpoints to manage movies, showtimes, tickets, and customers.

### API Endpoints

- **Movies:**
  - `GET /api/movies`
  - `POST /api/movies`
  - `PUT /api/movies/{id}`
  - `DELETE /api/movies/{id}`

- **Showtimes:**
  - `GET /api/showtimes`
  - `POST /api/showtimes`
  - `PUT /api/showtimes/{id}`
  - `DELETE /api/showtimes/{id}`

- **Tickets:**
  - `GET /api/tickets`
  - `POST /api/tickets`
  - `PUT /api/tickets/{id}`
  - `DELETE /api/tickets/{id}`

- **Customers:**
  - `GET /api/customers`
  - `POST /api/customers`
  - `PUT /api/customers/{id}`
  - `DELETE /api/customers/{id}`

## Screenshots

### Dashboard
![Dashboard](path_to_dashboard_image)

### Movie Management
![Movie Management](path_to_movie_management_image)

### Booking Tickets
![Booking Tickets](path_to_booking_tickets_image)

## Technologies Used

- **Java:** Backend programming language.
- **Spring Boot:** Framework for building the application.
- **MySQL:** Database management system.
- **Maven:** Build automation tool.
- **Swagger:** API documentation.

## Contributing

Contributions are welcome! Please read the [CONTRIBUTING.md](path_to_contributing_file) for guidelines on how to contribute to this project.

## License

This project is licensed under the MIT License. See the [LICENSE](path_to_license_file) file for details.
