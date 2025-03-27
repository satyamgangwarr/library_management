# 🏛️ Library Management System

A console-based Java application for librarians to manage book inventory with full CRUD operations.

## 📦 Features
- **Add/Remove Books** with validation
- **Search** by ID or title
- **Update** book details
- **View All Books** in catalog
- **Input Validation** (unique IDs, non-empty fields)

  ![Screenshot 2025-03-27 224608](https://github.com/user-attachments/assets/90ade08e-7dc6-4a05-ae83-436c7660a640)


## 🚀 Quick Start
# Compile & Run
javac -d out src/main/java/com/library/*.java src/main/java/com/library/**/*.java
java -cp out com.library.Main


##Project Structure
library-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── library/
│   │   │           ├── model/
│   │   │           │   └── Book.java
│   │   │           ├── exception/
│   │   │           │   ├── BookNotFoundException.java
│   │   │           │   └── InvalidBookException.java
│   │   │           ├── service/
│   │   │           │   └── LibraryService.java
│   │   │           ├── util/
│   │   │           │   └── ValidationUtil.java
│   │   │           └── Main.java
├── .gitignore
└── README.md
