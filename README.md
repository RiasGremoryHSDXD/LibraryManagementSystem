# Library Management System (AWT, Swing, & MySQL)


# Description
The Library Management System is a Java-based desktop application developed using AWT, Swing, and MySQL for backend data storage. It provides a user-friendly graphical interface for both administrators and users to manage books, borrowing, and returning activities effectively. By integrating MySQL (managed via phpMyAdmin), the system ensures secure and scalable data handling, making it suitable for small to medium-sized libraries.


# Key Features


# Admin Features
Add Book: Add books to the library with details like title, author, ISBN, and stock quantity.
Update Book: Modify existing book details, including title, author, and stock.
Delete Book: Remove books from the database when no longer available.
Generate Reports: Generate reports on inventory, borrowed books, and overdue items.


# User Features
View Available Books: Display a real-time list of books available for borrowing.
Search for a Book: Search books by title, author, or ISBN with advanced filtering options.
Borrow Book: Borrow available books, with transactions recorded in the database.
Return Book: Return books, updating their status and availability in the system.
View Borrowed Books: List all books borrowed by the logged-in user, with due dates.


# Technologies Used
• Java: Core programming language for business logic and GUI interaction.
• AWT & Swing: Create GUI components (buttons, tables, dialogs, etc.).
• MySQL: Backend database to store information on books, users, and transactions.
• phpMyAdmin: Manage the MySQL database through a web interface.
• JDBC: For database connectivity between the Java application and MySQL.


# Usage
This Library Management System is ideal for educational institutions or small libraries. It allows administrators to manage books efficiently while enabling users to interact seamlessly with the library.

# Entity Relationship Diagram (ERD) 
Link: https://drive.google.com/file/d/1bRGPtwV64nnJDxai8A2WKv5d0vAl2F7B/view?usp=sharing

# Import SQL file (Make sure you need to create a database named "library_management_system")

# After Setting the JAR file, you need to go in the DataBaseConnection.java file and change the root and password value into your database root name and the password
![image alt](https://github.com/RiasGremoryHSDXD/LibraryManagementSystem/blob/1ee155ef276c8eefa2d49ab06a10ec15d91cb9d2/image.png)
