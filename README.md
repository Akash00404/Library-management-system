# Library-management-system

This project is a simple Library Management System developed using Java Swing for the graphical user interface, JDBC for database connectivity, and MySQL as the backend database. It's designed to help library staff and members manage book inventories, handle book transactions (issuing and returning), and track overdue books.

-----

## Features 

  * **User Authentication**: A secure login screen for administrators. The system authenticates users against a `members` table in the `library` database.
  * **Member Management**: Add new members to the system through a dedicated form. You can also view all members in a table format.
  * **Book Management**:
      * Add new books to the library inventory.
      * View all books in a table.
      * Search for books by title or author, with the results displayed in a table.
  * **Transaction Management**:
      * Issue books to members, which updates the book quantity in the `books` table and creates a transaction record in the `txn` table.
      * Return books, with an automated fine calculation for overdue items based on a flat rate of $5 per day.
  * **Overdue Tracking**: A dedicated screen to view all currently issued and overdue books from the `txn` table.

-----

## Technologies Used 

  * **Frontend**: Java Swing
  * **Backend**: MySQL
  * **Connectivity**: JDBC (Java Database Connectivity)

-----

## Getting Started 

### 1\. Prerequisites

  * **Java Development Kit (JDK)**: Ensure you have JDK 8 or later installed.
  * **MySQL Server**: A running instance of a MySQL database.
  * **MySQL JDBC Driver**: The MySQL Connector/J library (`mysql-connector-j-8.0.33.jar`) is included in the project for Java-MySQL connectivity.

### 2\. Database Setup

First, you need to set up the database and tables. Open your MySQL terminal or a tool like MySQL Workbench and run the following SQL commands to create the database and schema. This schema has been corrected to resolve the "Unknown column" errors encountered, ensuring all column names match the Java code.

```sql
-- Create the database
CREATE DATABASE IF NOT EXISTS library;
USE library;

-- Create the members table
CREATE TABLE IF NOT EXISTS members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    password VARCHAR(100) NOT NULL
);

-- Create the books table
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    qty INT NOT NULL DEFAULT 0
);

-- Create the transaction table
CREATE TABLE IF NOT EXISTS txn (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    mem_id INT NOT NULL,
    issue_dt DATE NOT NULL,
    due_dt DATE NOT NULL,
    ret_dt DATE DEFAULT NULL,
    fine INT DEFAULT 0,
    status ENUM('ISSUED', 'RETURNED') NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (mem_id) REFERENCES members(id)
);

-- Insert a default admin user for login
INSERT INTO members (name, password) VALUES ('admin', 'admin123');
```

-----

### 3\. Java Application Configuration

You must update the `DB.java` file with your specific MySQL username and password to establish a connection.

```java
// DB.java
private static Connection con;

public static Connection get() throws Exception {
    if (con == null || con.isClosed()) {
        // Replace with your MySQL username and password
        con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/library", "root", "your_password"
        );
    }
    return con;
}
```

-----

-----

### 4\. Compiling and Running

To run the application from the command line, navigate to the directory where your Java files are located and use the `javac` and `java` commands.

```bash
# Compile all Java files with the JDBC driver in the classpath
javac -cp "path/to/your/mysql-connector-j-8.0.33.jar:." *.java

# Run the main application
java -cp "path/to/your/mysql-connector-j-8.0.33.jar:." LibraryApp
```

> **Note**: The classpath separator is a colon (`:`) on macOS/Linux and a semicolon (`;`) on Windows.

-----

## Project Structure 📂

The project is organized into several Java files, each dedicated to a specific function:

  * `LibraryApp.java`: The main entry point for the application, which launches the login screen.
  * `Login.java`: The user authentication screen. After a successful login, it opens the main dashboard.
  * `Menu.java`: The main dashboard for navigating to other features.
  * `DB.java`: A utility class for providing a single database connection instance across the application.
  * `Util.java`: A helper class for date and time-related conversions and calculations.
  * `AddBook.java`: A form to add books to the inventory.
  * `AddMember.java`: A form to add new members.
  * `ViewBooks.java`: A window that displays a list of all books in a table.
  * `ViewMembers.java`: A window that displays a list of all members.
  * `SearchBook.java`: A search interface for finding books by title or author.
  * `IssueBook.java`: A form to issue a book to a member.
  * `ReturnBook.java`: A form to handle book returns and apply fines for overdue items.
  * `Overdue.java`: A window to list all currently overdue books.
