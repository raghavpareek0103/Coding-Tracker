# 💻 Coding Tracker API

A RESTful backend application built with **Spring Boot** that allows users to track their coding practice questions with JWT-based authentication.

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java 17 | Programming Language |
| Spring Boot 3.2 | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-based Auth |
| MySQL | Database |
| Spring Data JPA / Hibernate | ORM |
| Lombok | Boilerplate Reduction |
| Maven | Build Tool |

## 🚀 Features

- ✅ JWT Authentication & Authorization
- ✅ BCrypt password encryption
- ✅ User registration & login
- ✅ CRUD operations for questions
- ✅ Filter questions by difficulty
- ✅ Statistics generation (Easy/Medium/Hard count)
- ✅ Global exception handling
- ✅ Input validation
- ✅ CORS configuration
- ✅ Pagination support

## 📋 API Endpoints

### 🔓 Auth (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register new user |
| POST | `/auth/login` | Login and get JWT token |

### 🔒 Questions (Requires JWT Token)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/questions/add` | Add a question |
| GET | `/questions/all/{userId}` | Get all questions |
| GET | `/questions/{id}` | Get question by ID |
| PUT | `/questions/update/{id}` | Update a question |
| DELETE | `/questions/delete/{id}` | Delete a question |
| GET | `/questions/stats/{userId}` | Get stats by difficulty |
| GET | `/questions/filter/{userId}?difficulty=EASY` | Filter by difficulty |

## 🔐 Authentication

All `/questions/**` endpoints are secured and require a valid JWT token.

**Step 1** - Register:
```
POST /auth/register
Body: { "name": "John", "email": "john@example.com", "password": "password123" }
```

**Step 2** - Login to get token:
```
POST /auth/login
Body: { "email": "john@example.com", "password": "password123" }
```

**Step 3** - Use token in every request header:
```
Authorization: Bearer <your_jwt_token>
```

> Without token you will get **403 Forbidden** response.

## ⚙️ Setup & Run Locally

### Prerequisites
- Java 17
- MySQL
- Maven

### Steps

**1. Clone the repo**
```bash
git clone https://github.com/raghavpareek0103/-Coding-Tracker.git
cd -Coding-Tracker
```

**2. Create MySQL database**
```sql
CREATE DATABASE coding_tracker;
```

**3. Create `src/main/resources/application.properties`**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/coding_tracker
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
jwt.secret=YOUR_JWT_SECRET_MINIMUM_32_CHARACTERS
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
server.port=8080
```

**4. Run the application**
```bash
mvn spring-boot:run
```

**5. API is available at**
```
http://localhost:8080
```

## 📁 Project Structure

```
src/main/java/com/tracker/
├── controller/
│   ├── AuthController.java
│   └── QuestionController.java
├── dto/
│   ├── LoginDto.java
│   └── RegisterDto.java
├── entity/
│   ├── Difficulty.java
│   ├── Question.java
│   └── User.java
├── exception/
│   └── GlobalExceptionHandler.java
├── repository/
│   ├── QuestionRepository.java
│   └── UserRepository.java
├── security/
│   ├── CorsConfig.java
│   ├── JwtAuthFilter.java
│   ├── JwtUtil.java
│   └── SecurityConfig.java
├── service/
│   ├── QuestionService.java
│   ├── UserService.java
│   └── impl/
│       ├── QuestionServiceImpl.java
│       └── UserServiceImpl.java
└── CodingTrackerApplication.java
```
### 📄 Pagination
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/questions/paged/{userId}?page=0&size=10` | Get paginated questions |
## 👨‍💻 Author

**Raghav Pareek**
- GitHub: [@raghavpareek0103](https://github.com/raghavpareek0103)