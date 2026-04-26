# Library Management System - Class Diagram

## Overview
This document contains the class diagram for the Library Management System, showing all classes and their relationships.

## PlantUML Source Code
The complete PlantUML source code is available in `class_diagram.puml`.

## Key Relationships

### Entity Relationships
- **Book** has a **BookStatus** and belongs to one **Branch**
- **Patron** can have multiple **Loans** and **Reservations**
- **Loan** connects a **Book** with a **Patron**
- **Reservation** connects a **Book** with a **Patron**

### Repository Pattern
- All repositories follow the interface-implementation pattern
- **RepositoryFactory** creates repository instances
- In-memory implementations: `*RepositoryImpl`

### Service Layer
- **LendingService**: Handles book checkout/return operations
- **CatalogService**: Manages book catalog
- **PatronService**: Manages patron operations
- **BranchService**: Manages library branches
- **ReservationService**: Handles book reservations
- **InventoryService**: Manages book inventory

### Observer Pattern
- **ReservationObserver** interface for notification system
- **ConsoleReservationObserver** implements console notifications
- **ReservationSubject** manages observer notifications

### Utility Classes
- **IdGenerator**: Generates unique IDs for entities
- **InputUtil**: Handles user input
- **ValidationUtil**: Validates data formats
- **AppConfig**: Application configuration

## Architecture Layers

```
┌─────────────────────────────────────┐
│           UI Layer                  │
│         (ConsoleApp)               │
├─────────────────────────────────────┤
│         Service Layer              │
│  (LendingService, CatalogService,  │
│   PatronService, etc.)             │
├─────────────────────────────────────┤
│        Repository Layer            │
│  (Repository interfaces & impls)   │
├─────────────────────────────────────┤
│          Entity Layer              │
│    (Book, Patron, Loan, etc.)      │
└─────────────────────────────────────┘
```

## Design Patterns Used

1. **Repository Pattern**: Data access abstraction
2. **Factory Pattern**: Repository creation
3. **Observer Pattern**: Reservation notifications
4. **Service Layer Pattern**: Business logic separation

## Class Diagram Visualization

To view the visual class diagram:

1. **Online Viewer**: Copy the content of `class_diagram.puml` and paste it into:
   - https://www.plantuml.com/plantuml/uml/
   - or https://editor.plantuml.com/

2. **Local Generation**: If you have PlantUML installed:
   ```bash
   plantuml class_diagram.puml
   ```

3. **IDE Integration**: Most IDEs have PlantUML plugins that can render the diagram directly.

## Key Points

- **Immutable IDs**: All entities have final ID fields
- **Type Safety**: Uses Optional for nullable returns
- **Validation**: Input validation through ValidationUtil
- **Observer Pattern**: For reservation notifications
- **In-memory Storage**: All repositories use in-memory implementation
- **Service Separation**: Clear separation of concerns in service layer
