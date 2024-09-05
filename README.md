# Brief 2 
# Library Management System v2

A simple Java application for managing a collection of books and magazines in a library. This application allows users to add, borrow, and search for documents, such as books and magazines, using the console interface.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Features](#features)

## Installation

### Prerequisites

- Java 8 or higher
- A terminal or command prompt

### Steps

1. **Clone the repository:**

   ```sh
   git clone https://github.com/Yorften/Brief-1.git
   cd Brief-1

2. **Run the command:**
   ```
   java -jar LibraryProject.jar

## Configuration

The application uses default settings to initialize the library with 20 seeded documents. If you want to modify the seed data, follow these steps:

### Edit the Seed Data:

In the Library class, find the seedLibrary method and adjust the predefined books and magazines as needed. You can change titles, authors, publication dates, and borrowing statuses.

### Change the Default Behavior:

You may modify any other configuration, such as changing menu options or customizing how documents are displayed, by editing the respective methods in the ConsoleUI class.


## Features

1. Add Documents: Add new books or magazines to the library.
2. List All Documents: View all available and borrowed documents.
3. Search Documents: Search for a specific document by title.
4. Borrow and Return Documents: Manage the borrowing and returning of documents.
5. Filter and Sort Documents: Filter documents by availability and sort using a custom comparator.