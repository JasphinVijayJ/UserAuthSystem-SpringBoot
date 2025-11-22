# 🔐 Full-Stack User Authentication System (React + Spring Boot + JWT)

A complete **full-stack authentication system** built using **React (Vite)** for the frontend and **Spring Boot 3 + JWT
** for the backend.  
Supports **User Registration**, **User Login**, **Admin Login**, **JWT Token Authentication**, **Protected Routes**, *
*Forgot Password with OTP**, and **Full Validation**.

---

# 🚀 Features

## 🎨 Frontend (React)

- 📝 User Registration with real-time validation
- 🔐 User & Admin Login
- ✉ OTP input + validation
- 🔑 Reset password with confirm password
- ✂ Auto-trim input fields
- 🎭 Role selection (USER / ADMIN)
- 💼 JWT token stored in `localStorage`
- 🔒 Protected Routes using custom components
- 🔁 Auto Redirect if already logged in
- 🚪 Logout clears token + redirects
- 📱 Fully responsive design
- 🧾 **Forgot Password Workflow**
    - Send OTP to email
    - Verify OTP
    - Reset password (after OTP verification)
    - Error messages & success toasts

---

## 🛡 Backend (Spring Boot + JWT)

- Spring Boot 3.5.5 REST API
- JWT Token Generation, Validation
- Role-based authentication (ADMIN / USER)
- Full validation using Jakarta Validation
- Custom Exceptions + Global Exception Handler
- Unique Email + Phone checks
- Password confirmation validation
- DTO-based clean API
- MySQL Database
- 📩 **Forgot Password (OTP) System**
    - Generate 6-digit OTP
    - Expire OTP after 5 minutes
    - OTP verification endpoint
    - Reset password endpoint
    - Auto delete OTP after use
    - Email sending system (EmailService)
    - Secure flow: OTP → Verify → Reset

---

# 🧰 Tech Stack

## Frontend

- React (Vite)
- React Router DOM
- JavaScript (ES6+)
- CSS
- localStorage
- Fetch API

## Backend

- Java 21
- Spring Boot 3.5.5
- Spring Data JPA
- MySQL
- JWT (jjwt)
- Jakarta Validation
- Maven

---

# 📂 Project Structure

## Frontend (React)

```
frontend/
├── public/
├── src/
│   ├── components/
│   │   ├── InputField.jsx
│   │   ├── LogoutButton.jsx
│   │   └── ProtectiveRoute.jsx
│   ├── pages/
│   │   ├── Login.jsx
│   │   ├── Register.jsx
│   │   ├── Dashboard.jsx
│   │   ├── AdminDashboard.jsx
│   │   ├── ForgotPassword.jsx
│   │   ├── VerifyOtp.jsx
│   │   └── ResetPassword.jsx
│   ├── styles/
│   │   └── App.css
│   ├── App.jsx
│   └── main.jsx
├── package.json
└── vite.config.js
```

---

## Backend (Spring Boot)

```
com.uas
├── controller
│   ├── AdminController.java
│   ├── UserController.java
│   └── ForgotPasswordController.java
├── dto
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── ForgotPasswordRequest.java
│   ├── VerifyOtpRequest.java
│   └── ResetPasswordRequest.java
├── enums
│   ├── Role.java
│   ├── ErrorMessage.java
│   └── SuccessMessage.java
├── exception
│   ├── GlobalExceptionHandler.java
│   ├── EmailAlreadyExistsException.java
│   ├── PhoneAlreadyExistsException.java
│   ├── PasswordMismatchException.java
│   ├── UserNotFoundException.java
│   ├── InvalidPasswordException.java
│   ├── InvalidOtpException.java
│   ├── OTPExpiredException.java
│   └── OTPVerificationRequiredException.java
├── model
│   ├── User.java
│   ├── Admin.java
│   └── ForgotPassword.java
├── repository
│   ├── UserRepository.java
│   ├── AdminRepository.java
│   └── ForgotPasswordRepository.java
├── security
│   └── JwtUtil.java
└── service
    ├── UserService.java
    ├── AdminService.java
    └── ForgotPasswordService.java
```

---

# 🌐 API Endpoints

## User APIs → `/uas/user`

| Method | Endpoint    | Description       |
|--------|-------------|-------------------|
| POST   | `/register` | Register new user |
| POST   | `/login`    | User login        |

## Admin APIs → `/uas/admin`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/login` | Admin login |

## Forgot Password APIs → `/uas/user/password`

| Method | Endpoint  | Description            |
|--------|-----------|------------------------|
| POST   | `/forgot` | Send OTP to user email |
| POST   | `/verify` | Verify OTP             |
| POST   | `/reset`  | Reset password         |

---

# 📝 Sample JSON

## Registration

```json
{
  "name": "John Doe",
  "phone": "9876543210",
  "email": "john@example.com",
  "password": "password123",
  "confirmPassword": "password123"
}
```

## Login

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

## Forgot Password – Send OTP

```json
{
  "email": "john@example.com"
}
```

## Verify OTP

```json
{
  "email": "john@example.com",
  "otp": "123456"
}
```

## Reset Password

```json
{
  "email": "john@example.com",
  "newPassword": "new12345",
  "confirmNewPassword": "new12345"
}
```

---

# 🔑 JWT Login Response

```json
{
  "token": "ey123....",
  "role": "USER",
  "message": "Login successful."
}
```

---

# ⚠️ Backend Error Handling

| Status | Meaning                           |
|--------|-----------------------------------|
| 400    | Invalid input / Validation failed |
| 401    | Incorrect password                |
| 404    | User/Admin not found              |
| 409    | Email/Phone already exists        |
| 410    | OTP expired                       |
| 422    | OTP not verified                  |

Handled by **GlobalExceptionHandler**.

---

# 🛠 How to Run

## Backend (Spring Boot)

### Configure MySQL

`application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/uas_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

### Run Backend

```bash
./mvnw spring-boot:run
```

Backend URL:

```
http://localhost:8080/uas
```

---

## Frontend (React)

### Install packages

```bash
npm install
```

### Run React App

```bash
npm run dev
```

Frontend URL:

```
http://localhost:5173
```

---

# 🔒 Protected Routes (Frontend Behavior)

- If not logged in → redirect to `/login`
- If user role ≠ admin → block admin routes
- JWT auto-attached to protected API requests
- Forgot Password flow works without login

---

# ⚡ Notes

- JWT expires in **1 hour**
- Frontend trims all input values
- Backend validates everything again (secure)
- Admin login has separate dashboard
- OTP expires in **5 minutes**
- OTP deleted after verified or expired

---

# 📝 Author

**Jasphin Vijay J**  
📧 *jasphinvijayj@gmail.com*
