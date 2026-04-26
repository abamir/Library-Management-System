# 📚 Library Management System (Java Console Application)

## 📖 Overview
This project is a **Java console-based Library Management System** developed as a **Low-Level Design (LLD)** assignment.

It enables librarians to manage:
- Books
- Patrons
- Lending operations
- Inventory
- Library branches
- Reservations

### 🎯 Key Learning Outcomes
- Object-Oriented Programming (OOP)
- SOLID Principles
- Design Patterns
- Java Collections
- Clean Architecture & Extensibility

---

## 🚀 Features

### 🔹 Core Features

#### 📘 Book Management
- Add, update, and remove books
- Search books by title, author, or ISBN
- View all books

#### 👤 Patron Management
- Add and update patrons
- View all patrons
- Track borrowing history

#### 🔄 Lending Process
- Checkout books
- Return books
- Track loan history
- Enforce **maximum 3 active loans per patron**

#### 📦 Inventory Management
- View available books
- View borrowed books
- View reserved books

---

### 🔹 Advanced Features (Extensions)

#### 🏢 Multi-Branch Support
- Add and list library branches
- Transfer books between branches
- Checkout books from a specific branch
- View branch-wise inventory

#### 📌 Reservation System
- Reserve books that are currently unavailable
- Maintain reservation queue (**FIFO**)
- Notify patrons when a reserved book becomes available
- Restrict checkout to the notified patron

---

## 🛠️ Technology Stack
- **Java 17**
- **Maven**
- **SLF4J + Logback** (Logging)

---

## 📂 Project Structure

```text
Library-Management-System
├── src/main/java
│   └── com/aitribe/lms
│       ├── Main.java
│       ├── configurations
│       │   └── AppConfig.java
│       ├── Data
│       │   ├── Docs/
│       │   └── README.md
│       ├── entity
│       │   ├── Book.java
│       │   ├── Branch.java
│       │   ├── Loan.java
│       │   ├── Patron.java
│       │   └── Reservation.java
│       ├── enums
│       │   ├── BookStatus.java
│       │   └── ReservationStatus.java
│       ├── repository
│       │   ├── BookRepository.java
│       │   ├── BranchRepository.java
│       │   ├── LoanRepository.java
│       │   ├── PatronRepository.java
│       │   ├── RepositoryFactory.java
│       │   ├── ReservationRepository.java
│       │   └── inmemory
│       │       ├── BookRepositoryImpl.java
│       │       ├── BranchRepositoryImpl.java
│       │       ├── LoanRepositoryImpl.java
│       │       ├── PatronRepositoryImpl.java
│       │       └── ReservationRepositoryImpl.java
│       ├── service
│       │   ├── BranchService.java
│       │   ├── CatalogService.java
│       │   ├── InventoryService.java
│       │   ├── LendingService.java
│       │   ├── PatronService.java
│       │   ├── ReservationService.java
│       │   ├── observer
│       │   │   ├── ConsoleReservationObserver.java
│       │   │   ├── ReservationObserver.java
│       │   │   └── ReservationSubject.java
│       │   └── serviceImpl
│       │       ├── BranchServiceImpl.java
│       │       ├── CatalogServiceImpl.java
│       │       ├── InventoryServiceImpl.java
│       │       ├── LendingServiceImpl.java
│       │       ├── PatronServiceImpl.java
│       │       └── ReservationServiceImpl.java
│       ├── ui
│       │   ├── AbstractManu.java
│       │   ├── AppContext.java
│       │   ├── BookMenu.java
│       │   ├── BranchesMenu.java
│       │   ├── Command.java
│       │   ├── ConsoleApp.java
│       │   ├── InventoryMenu.java
│       │   ├── LendingMenu.java
│       │   ├── PatronMenu.java
│       │   ├── ReservationMenu.java
│       │   └── SimpleCommand.java
│       └── Util
│           ├── IdGenerator.java
│           ├── InputUtil.java
│           └── ValidationUtil.java
└── src/main/resources
    └── logback.xml

```

# 🧠 OOP Concepts Used 

- Encapsulation → Private fields with controlled access.
- Repository and service interfaces
- Polymorphism → Interface-based implementations
- Reusability → Shared abstract menu for console UI

# 📐 SOLID Principles Applied
- SRP → Each class has a single responsibility
- OCP → Extensible without modifying existing code
- LSP → Implementations follow interface contracts
- ISP → Fine-grained interfaces
- DIP → Depends on abstractions, not implementations

🧩 Design Patterns Used

1. Factory Pattern
   RepositoryFactory creates repository instances
2. Command Pattern
   Menu actions implemented as commands
   Decouples UI from business logic
3. Observer Pattern
   Reservation notification system

Components:

ReservationServiceImpl → Subject
ConsoleReservationObserver → Observer

# 🧪 Sample Data
## 🏢 Branches
- BR-001 – Main Branch
- BR-002 – City Branch
- BR-003 – Tech Park Branch
## 📚 Books
- ISBN-001 – Clean Code
- ISBN-002 – Effective Java
- ISBN-003 – Design Patterns
- ISBN-004 – Refactoring
- ISBN-005 – Java Concurrency in Practice
- ISBN-006 – Head First Design Patterns
## 👥 Patrons
- P-001 – Amir
- P-002 – Rahul
- P-003 – Sneha


