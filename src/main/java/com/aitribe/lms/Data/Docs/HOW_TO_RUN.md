# ▶️ How to Run - Library Management System

This guide explains how to build and run the **Library Management System (Java Console Application)** on your machine.

---

## ✅ Prerequisites

Ensure you have the following installed:

- **Java 17 or higher**
- **Maven 3.x**
- Any IDE (IntelliJ IDEA / Eclipse / VS Code)

Verify installations:

```bash
java -version
mvn -version
```

# 📥 Step 1: Get the Project
Option 1: Clone Repository

```Bash
git clone <your-repository-url>
cd <project-folder>
```
### Option 2: Download ZIP
- Download the project ZIP
- Extract it
- Open the folder in your IDE

# 🔧 Step 2: Build the Project

```Bash
mvn clean package
```

## 🔹 Option 2: Run using Maven

```Bash
mvn exec:java -Dexec.mainClass="com.lms.Main"
```

## 🔹 Option 3: Run using JAR

```Bash
java -jar target/<your-jar-file-name>.jar
```