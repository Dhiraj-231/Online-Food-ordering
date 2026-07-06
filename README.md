# Online Food Ordering Application

A full-stack online food ordering platform with a Spring Boot backend and React.js frontend. Users can browse restaurants, view menus, place orders, and track delivery. Admin users can manage restaurants, menus, and orders.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [System Architecture](#system-architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Database Setup](#database-setup)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [User Roles](#user-roles)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## Features

### Customer Features
- User registration and authentication
- Browse restaurants and food menus
- Search and filter restaurants by category
- Add items to cart and manage cart
- Place orders with delivery address
- Track order status in real-time
- View order history
- User profile management

### Restaurant Owner Features
- Register and manage restaurant details
- Add/edit food items and menus
- Manage restaurant categories and ingredients
- View incoming orders
- Update order status
- Analytics on orders and sales

### Admin Features
- Manage all restaurants
- Manage all users and their roles
- Monitor orders and transactions
- Manage food categories and ingredients
- System-wide analytics and reporting

## Technologies

### Backend Stack
| Technology | Version | Purpose |
|-----------|---------|---------|
| ☕ **Java** | 21 | Programming Language |
| 🍃 **Spring Boot** | 3.x | Backend Framework |
| 🗄️ **MySQL** | 8.0+ | Database |
| 🔄 **JPA/Hibernate** | Latest | ORM Framework |
| 🔐 **Spring Security** | Latest | Authentication & JWT |
| 🔨 **Maven** | 3.8+ | Build Tool |
| 💳 **Stripe API** | Latest | Payment Processing |
| 📦 **Lombok** | Latest | Code Generation |

### Frontend Stack
| Technology | Version | Purpose |
|-----------|---------|---------|
| ⚛️ **React.js** | 18.2+ | UI Library |
| 📦 **Redux** | Latest | State Management |
| 🛣️ **React Router** | v6 | Routing |
| 🌐 **Axios** | Latest | HTTP Client |
| 🎨 **Material-UI** | 5.x+ | UI Components |
| 💄 **Tailwind CSS** | 3.x+ | Styling Framework |
| 🎭 **Styled Components** | Latest | CSS-in-JS |
| 📦 **npm** | Latest | Package Manager |

### Full Tech Stack Overview

```
🖥️ Frontend Layer              🔧 Backend Layer           💾 Data Layer
┌────────────────────┐        ┌──────────────────┐       ┌──────────────┐
│ ⚛️  React 18.2     │        │ 🍃 Spring Boot   │       │ 🗄️  MySQL    │
│ 📦 Redux           │   -->  │ ☕ Java 21       │  -->  │ 📊 JPA/HB    │
│ 🎨 Tailwind CSS    │        │ 🔐 Spring Sec    │       │              │
│ 🎭 Styled Comp     │        │ 💳 Stripe API    │       │              │
│ 📦 npm/Node.js     │        │ 🔨 Maven         │       │              │
└────────────────────┘        └──────────────────┘       └──────────────┘
      Port 3002                  Port 8081               Port 3306
```

## System Architecture

```
┌─────────────────────────────────────────┐
│     React Frontend (Port 3002)          │
│  - User Interface & Components          │
│  - Redux State Management               │
│  - Authentication Flow                  │
└──────────────────┬──────────────────────┘
                   │ HTTP/REST
                   ▼
┌─────────────────────────────────────────┐
│   Spring Boot Backend (Port 8081)       │
│  - REST API Controllers                 │
│  - Business Logic & Services            │
│  - JWT Authentication                   │
│  - Database Operations                  │
└──────────────────┬──────────────────────┘
                   │ JDBC
                   ▼
┌─────────────────────────────────────────┐
│      MySQL Database (Port 3306)         │
│  - User Data                            │
│  - Restaurant & Menu Information        │
│  - Orders & Transactions                │
└─────────────────────────────────────────┘
```

## Getting Started

### Prerequisites

- **Java Development Kit (JDK):** Version 21 or higher
  - Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/#java21)
  - Verify: `java -version`

- **Node.js & npm:** Version 14 or higher
  - Download from [nodejs.org](https://nodejs.org/)
  - Verify: `node -v` and `npm -v`

- **MySQL Server:** Version 8.0 or higher
  - Download from [mysql.com](https://www.mysql.com/downloads/)
  - Verify: `mysql -u root -p -e "SELECT VERSION();"`

- **Git:** Version control
  - Download from [git-scm.com](https://git-scm.com/)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Dhiraj-231/Online-Food-ordering.git
   cd Online-Food-ordering
   ```

2. **Install backend dependencies:**
   ```bash
   cd Backends
   mvn clean install
   ```

3. **Install frontend dependencies:**
   ```bash
   cd ../frontend
   npm install
   ```

### Database Setup

1. **Create MySQL database:**
   ```bash
   mysql -u root -p
   ```

2. **In MySQL console:**
   ```sql
   CREATE DATABASE food_ordering;
   USE food_ordering;
   ```

3. The application will automatically create tables on first run via Hibernate (ddl-auto=update)

### Configuration

The application uses environment-based configuration files:

**Backend Configuration** (`Backends/src/main/resources/application.properties`):
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/food_ordering
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
stripe.secret.key=sk_test_your_key_here
```

**Update these values for your environment:**
- Database credentials
- Stripe API key for payment processing
- Email SMTP configuration if needed

### Running the Application

#### Option 1: Run Backend and Frontend Separately

**Terminal 1 - Start Backend:**
```bash
cd Backends
mvn spring-boot:run
# Backend will start on http://localhost:8081
```

**Terminal 2 - Start Frontend:**
```bash
cd frontend
npm start
# Frontend will automatically open on http://localhost:3002
# (or next available port if 3002 is in use)
```

#### Option 2: Build and Deploy

**Backend:**
```bash
cd Backends
mvn clean package
java -jar target/Online-Food-ordering-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
cd frontend
npm run build
# Output in frontend/build directory
```

### Accessing the Application

- **Frontend UI:** http://localhost:3002
- **Backend API:** http://localhost:8081/api
- **API Swagger Docs:** http://localhost:8081/swagger-ui.html (if enabled)

## API Documentation

### Authentication Endpoints
- `POST /auth/signup` - Register new user
- `POST /auth/signin` - Login user
- `POST /auth/logout` - Logout user

### Restaurant Endpoints
- `GET /api/restaurants` - List all restaurants
- `GET /api/restaurants/{id}` - Get restaurant details
- `GET /api/restaurants/{id}/menu` - Get restaurant menu

### Order Endpoints
- `POST /api/orders` - Place new order
- `GET /api/orders` - Get user's orders
- `GET /api/orders/{id}` - Get order details
- `PUT /api/orders/{id}/status` - Update order status (admin only)

### User Endpoints
- `GET /api/users/profile` - Get current user profile
- `PUT /api/users/profile` - Update user profile
- `GET /api/users` - List all users (admin only)

For complete API documentation, refer to controller classes in `Backends/src/main/java/com/Dhiraj/Online/Food/ordering/Controller/`

## Project Structure

```
Online-Food-ordering/
├── Backends/                          # Spring Boot Backend
│   ├── src/main/
│   │   ├── java/com/Dhiraj/Online/Food/ordering/
│   │   │   ├── Controller/           # REST Controllers
│   │   │   ├── Service/              # Business Logic
│   │   │   ├── Repository/           # Data Access Layer
│   │   │   ├── Model/                # Entity Classes
│   │   │   ├── Domin/                # Enums (USER_ROLE, OrderStatus)
│   │   │   ├── Config/               # Configuration Classes
│   │   │   └── Exception/            # Custom Exceptions
│   │   └── resources/
│   │       └── application.properties # Configuration
│   └── pom.xml                        # Maven Dependencies
│
├── frontend/                          # React Frontend
│   ├── src/
│   │   ├── components/               # React Components
│   │   ├── pages/                    # Page Components
│   │   ├── redux/                    # Redux Store & Slices
│   │   ├── utils/                    # Utility Functions
│   │   ├── styles/                   # CSS/Styled Components
│   │   └── App.js                    # Main App Component
│   └── package.json                  # NPM Dependencies
│
├── README.md                          # This file
└── LICENSE                            # MIT License
```

## User Roles

The application supports three main user roles:

| Role | Permissions |
|------|------------|
| **ROLE_CUSTOMER** | Browse restaurants, place orders, view history, manage profile |
| **ROLE_RESTAURANT_OWNER** | Manage restaurant, update menus, view/update orders |
| **ROLE_RESTAURANT_MANAGER** | Manage restaurant operations, update orders |
| **ROLE_ADMIN** | Full system access, manage users, restaurants, and orders |

## Troubleshooting

### Common Issues

**Issue: "Database connection refused"**
```
Solution: Ensure MySQL is running and credentials are correct in application.properties
- Check MySQL service: mysql -u root -p
- Verify database exists: SHOW DATABASES;
```

**Issue: "Port 3002 already in use"**
```
Solution: Frontend will automatically use next available port (3003, 3004, etc.)
Or kill the process using the port:
- Windows: netstat -ano | findstr :3002
- Linux/Mac: lsof -i :3002
```

**Issue: "Maven compilation errors"**
```
Solution: Clear and rebuild
- cd Backends
- mvn clean install
- mvn spring-boot:run
```

**Issue: "npm packages not installing"**
```
Solution: Clear npm cache and reinstall
- npm cache clean --force
- rm -rf node_modules package-lock.json
- npm install
```

**Issue: "JWT Token expired"**
```
Solution: Re-login or check JwtConstant.java for token expiration time
Adjust if needed in backend configuration
```

### Enable Debug Logging

To see detailed logs, update `application.properties`:
```properties
logging.level.root=INFO
logging.level.com.Dhiraj.Online.Food.ordering=DEBUG
logging.level.org.springframework.security=DEBUG
```

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Standards
- Follow Java/JavaScript naming conventions
- Write meaningful commit messages
- Add comments for complex logic
- Test your changes before submitting PR

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

**Last Updated:** July 2026
**Maintainer:** Dhiraj-231
