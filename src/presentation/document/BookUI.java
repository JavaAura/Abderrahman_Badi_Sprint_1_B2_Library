package src.presentation.document;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import src.utils.InputValidator;

import src.business.Book;
import src.business.Student;

import src.dao.interfaces.BookDAO;
import src.dao.interfaces.DocumentDAO;
import src.dao.interfaces.ReservationDAO;
import src.dao.interfaces.StudentDAO;

import src.services.DocumentService;
import src.services.reservation.ReservationDAOImpl;
import src.services.user.StudentDAOImpl;

import src.presentation.user.UserUI;

public class BookUI {
    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    static StudentDAO studentDAO = new StudentDAOImpl();
    static ReservationDAO reservationDAO = new ReservationDAOImpl();
    static DocumentService documentService = new DocumentService(reservationDAO);

    static int selectedId = -1;

    public static Book bookList(List<Book> books) {
        Book selectedBook = null;
        int selectedId;
        do {
            if (books.size() == 0) {
                System.out.println(
                        "+--------------------------------------------------------------------------------------+");
                System.out.println(
                        "|                                    No books found                                    |");
                System.out.println(
                        "+--------------------------------------------------------------------------------------+\n\n\n");

                return selectedBook; // automatically goes back to last Menu
            }

            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "| Id |           Title          |          Author          |   Pages   | Publication Date | Book Number |");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------+");
            for (Book book : books) {
                System.out.printf("| %-3d| %-24s | %-24s | %-9d |    %-10s    | %-11d |\n", book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPageNumbers(),
                        book.getPublicationDate(),
                        book.getNumber());
                System.out.println(
                        "+-------------------------------------------------------------------------------------------------------+");
            }

            System.out.print(
                    "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
            try {
                selectedId = in.nextInt();
                if (selectedId == 0)
                    return selectedBook;
                for (Book book : books) {
                    if (book.getId() == selectedId) {
                        selectedBook = book;
                        break;
                    }
                }
                if (selectedBook == null) {
                    System.out.println("Invalid ID. Please try again.");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                in.next();
            }
        } while (selectedBook == null);
        return selectedBook;
    }

    public static void handleChoice(int input, Book book, DocumentDAO documentDAO, BookDAO bookDAO) {
        switch (input) {
            case 1:
                String title = InputValidator.promptAndParseNullableString("Title : ");
                String author = InputValidator.promptAndParseNullableString("Author : ");
                LocalDate publicationDate = InputValidator
                        .promptAndParseNullableDate("Publication date : ");
                Integer pageNumbers = InputValidator.promptAndParseNullableInt("Page numbers : ");
                Integer number = InputValidator.promptAndParseNullableInt("Book number : ");

                bookDAO.update(book,
                        new String[] { title, author,
                                (publicationDate != null) ? publicationDate.toString() : null,
                                (pageNumbers != null) ? pageNumbers.toString() : null,
                                (number != null) ? number.toString() : null });
                break;
            case 2:
                documentDAO.delete(book);
                break;
            case 3:
                if (reservationDAO.isReserved(book))
                    break;
                List<Student> students = studentDAO.getAll();
                UserUI.StudentList(students);

                Student selectedStudent = null;

                do {
                    System.out.print(
                            "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
                    try {
                        selectedId = in.nextInt();
                        if (selectedId == 0)
                            break;
                        for (Student student : students) {
                            if (student.getId() == selectedId) {
                                selectedStudent = student;
                                break;
                            }
                        }
                        if (selectedStudent == null) {
                            System.out.println("Invalid ID. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        in.next();
                    }
                } while (selectedStudent == null);

                if (selectedStudent == null)
                    break;

                documentService.reserveDocument(book, selectedStudent);
                break;
            default:
                break;
        }
    }
}
