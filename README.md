# Bank Management System

A Java console-based banking application with MySQL backend.

## Features
- Employee login: add customers, create accounts, view transactions
- Customer login: deposit, withdraw, check balance, mini statement

## Tech Stack
- Java 17
- MySQL 8
- JDBC
- Maven

## Project Structure
```
src/
└── main/
└── java/
└── com.bank/
├── dao/        → AccountDAO, UserDAO
├── model/      → Customer, Account, Transaction, User, Employees
├── service/    → All service interfaces and implementations
├── util/       → Database connection
└── main/       → Main.java (entry point)
```
## Setup
1. Create MySQL database using the provided SQL scripts
2. Copy `db.properties.example` to `db.properties` and add your credentials:
   db.url=jdbc:mysql://localhost:3306/bank
   db.user=root
   db.password=your_password
3. Run `Main.java`

## Login Credentials (for testing)
| Username | Password | Role     |
|----------|----------|----------|
| admin1   | pass123  | employee |
| bob      | pass123  | customer |
