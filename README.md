# Brief 2 
# Library Management System v2

A simple Java application for managing a collection of books and magazines in a library. This application allows users to add, borrow, and search for documents, such as books and magazines, using the console interface.

## Table of Contents

- [Installation](#installation)
- [Structure](#Structure)
- [Features](#features)

## Installation

### Prerequisites

- Java 8 or higher
- PostgreSQL JDBC Driver
- A terminal or command prompt

### Setup environment variable

1. **For windows:**
   ```cmd
   set DB_URL= **Your postgreSQL URL**
   set DB_USER= **DB User**
   set DB_PASSWORD = **LEAVE EMPTY IF NO PASSWORD**

2. **For linux based:**
   ```bash
   export DB_URL= **Your postgreSQL URL**
   export DB_USER= **DB User**
   export DB_PASSWORD = **LEAVE EMPTY IF NO PASSWORD**

### Setup your database:

1. Ensure your PostgreSQL server is running.
2. Navigate to the directory containing `Database.sql`.
3. Run the following command to create the database and tables:
   ```bash
   psql -U your_username -f Database.sql
   

### Steps

1. **Clone the repository:**

   ```sh
   git clone https://github.com/Yorften/Brief-1.git
   cd Brief-1

2. **Compile the project:**
   ```sh
   javac -d out src/**/*.java

3. **Run the command:**
   ```sh
   jar cfm YourApp.jar MANIFEST.MF -C out/ .

4. **Run the application:**
   ```sh
   java -jar YourApp.jar

## Structure

- **DAO Layer**: Handles database interactions for CRUD operations on users, documents, and reservations.
- **Model Layer**: Defines entities such as `User`, `Document`, and `Reservation`, including specific document types like `Book`, `Magazine`, etc.
- **Service Layer**: Manages core business logic and acts as an intermediary between the DAO and user input.
- **Presentation Layer**: Manages the interaction with the end-user, this layer includes input handling, menu navigation, and output formatting. Examples are the methods that display lists of users, documents, or prompt the user for input. Classes related to this layer include `UserMenu`, `MainMenu`, etc. 

## Features

1. User Management: The application allows the management of users such as students and professors. Users can be listed, added, updated, and deleted from the system.
2. Document Management: The system supports managing different types of documents like books, theses, magazines, and journals. Users can search, list, and modify document details.
3. Reservation System: Users (students or professors) can reserve documents, and the system keeps track of active or canceled reservations, including borrowed documents and their return dates.
4. Search Functionality: The application allows for partial matching when searching for documents by title or author.
5. Database Management: The application interacts with a PostgreSQL database to store, retrieve, update, and delete information related to users, documents, and reservations.
6. Error Handling and Input Validation: Input from users is validated, and exceptions are caught to prevent application crashes. For example, invalid dates or null inputs are handled gracefully.
