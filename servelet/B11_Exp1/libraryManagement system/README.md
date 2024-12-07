
# jsp-library-management-system
A simple JSP web app with following features:

 - Add Books
 - Add Students
 - Issue Books to Students
 - View All Issued Books

A student can only be issued upto 3 books.

## Table Schema
```
CREATE TABLE Books (  
id SERIAL PRIMARY KEY,  
title VARCHAR(255) NOT NULL,  
author VARCHAR(255) NOT NULL,  
isbn VARCHAR(20) UNIQUE NOT NULL  
);  
  
CREATE TABLE Students (  
id SERIAL PRIMARY KEY,  
name VARCHAR(255) NOT NULL,  
email VARCHAR(255) UNIQUE NOT NULL,  
issuedBooks INT DEFAULT 0  
);  
  
CREATE TABLE IssuedBooks (  
id SERIAL PRIMARY KEY,  
bookId INT,  
studentId INT,  
FOREIGN KEY (bookId) REFERENCES Books(id),  
FOREIGN KEY (studentId) REFERENCES Students(id)  
);
```
**Note:**
- Username: Postgres
- Password: Postgres