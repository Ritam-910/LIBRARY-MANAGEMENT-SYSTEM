# 📚 Library Management System - Java

A modern Library Management System built with Java Swing, following Object-Oriented Programming principles. The system provides a graphical user interface for managing books, users, and book transactions.


## Features

- **Book Management**
  - Add new books to the library
  - View all available books
  - Track book availability status

- **User Management**
  - Add new library users
  - View all registered users

- **Transaction Management**
  - Issue books to users
  - Return books to the library
  - Track book availability in real-time

- **Modern GUI**
  - Intuitive tabbed interface
  - Responsive design
  - System message logging

## Technologies Used

- Java 8+
- Java Swing for GUI
- Object-Oriented Design Principles
- MVC (Model-View-Controller) pattern

## Class Structure

The system consists of three main classes:

1. **Book.java** - Represents book entities with:
   - ID, title, author
   - Availability status

2. **User.java** - Represents library users with:
   - ID and name

3. **Library.java** - Core library operations:
   - Maintains collections of books and users
   - Handles book issuing and returning
   - Provides search functionality

## GUI Features

The **LibraryGUI.java** class provides:

- Tabbed interface for different operations
- Form validation and error handling
- Real-time system message logging
- Clean, modern interface with:
  - Custom-styled buttons
  - Responsive layouts
  - Interactive elements

## How to Run

1. Ensure you have Java JDK 8 or later installed
2. Clone this repository
3. Compile and run the `Main.java` file

```bash
javac *.java
java Main
