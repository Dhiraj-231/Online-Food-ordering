# Online Food Ordering Application

This is an online food ordering application with a Spring Boot backend and a React.js frontend. The application allows users to browse restaurant menus, place orders, and manage their accounts.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features
- User authentication and authorization
- Browse restaurant menus
- Place and track orders
- View order history
- Admin panel for managing menus and orders

## Technologies
- **Backend:** Spring Boot, Spring Security, JPA/Hibernate, MySQL
- **Frontend:** React.js, Redux, React Router, Axios
- **Build Tools:** Maven (Backend), npm/yarn (Frontend)

## Getting Started

### Prerequisites
- Java 11 or higher
- Node.js (v14 or higher) and npm (v6 or higher)
- MySQL database

### Backend Setup
1. Clone the repository:
    ```sh
    git clone https://github.com/Dhiraj-231/Online-Food-ordering.git
    cd Online-Food-ordering/backend
    ```

2. Update the `application.properties` file with your MySQL database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

3. Build and run the backend:
    ```sh
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

### Frontend Setup
1. Navigate to the frontend directory:
    ```sh
    cd ../frontend
    ```

2. Install the dependencies:
    ```sh
    npm install
    ```

3. Start the development server:
    ```sh
    npm start
    ```

## Usage
1. Open your browser and navigate to `http://localhost:3000` to access the frontend.
2. The backend API will be available at `http://localhost:8080`.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code follows the project's coding standards and includes appropriate tests.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
