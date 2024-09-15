package src.presentation.document;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import src.business.Book;
import src.business.Document;
import src.business.Student;
import src.dao.interfaces.BookDAO;
import src.dao.interfaces.DocumentDAO;
import src.dao.interfaces.MagazineDAO;
import src.dao.interfaces.ProfessorDAO;
import src.dao.interfaces.ReservationDAO;
import src.dao.interfaces.ScientificJournalDAO;
import src.dao.interfaces.StudentDAO;
import src.dao.interfaces.UniversityThesisDAO;
import src.presentation.user.UserInterface;
import src.services.DocumentService;
import src.services.reservation.ReservationDAOImpl;
import src.services.user.ProfessorDAOImpl;
import src.services.user.StudentDAOImpl;
import src.utils.InputValidator;

public class DocumentInterface {

    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    static StudentDAO studentDAO = new StudentDAOImpl();
    static ProfessorDAO professorDAO = new ProfessorDAOImpl();
    static ReservationDAO reservationDAO = new ReservationDAOImpl();
    static DocumentService documentService = new DocumentService(reservationDAO);
    static int selectedId = -1;

    public static int DocumentList(List<Document> documents) {
        int input = -1;
        do {
            if (documents.size() == 0) {
                System.out.println(
                        "+--------------------------------------------------------------------------------------+");
                System.out.println(
                        "|                                  No documents found                                  |");
                System.out.println(
                        "+--------------------------------------------------------------------------------------+\n\n\n");
                return 5; // automatically goes back to Documents Menu
            }

            System.out.println(
                    "+-----------------------------------------------------------------------------------------+");
            System.out.println(
                    "| Id |           Title          |          Author          |   Pages   | Publication Date |");
            System.out.println(
                    "+-----------------------------------------------------------------------------------------+");
            for (Document document : documents) {
                System.out.printf("| %-3d| %-24s | %-24s | %-9d |    %-10s    |\n", document.getId(),
                        document.getTitle(),
                        document.getAuthor(),
                        document.getPageNumbers(),
                        document.getPublicationDate());
                System.out.println(
                        "+-----------------------------------------------------------------------------------------+");
            }

            System.out.println("\t\t+----------------------------------------+");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t|     1- List all books                  |");
            System.out.println("\t\t|     2- List all magazines              |");
            System.out.println("\t\t|     3- List all journals               |");
            System.out.println("\t\t|     4- List all thesis                 |");
            System.out.println("\t\t|     5- Back                            |");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t+----------------------------------------+");
            System.out.print("Pick your choice : ");

            try {
                input = in.nextInt();
                if (input < 1 || input > 5) {
                    System.out.println("Please pick a choice between 1 and 5...");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Please pick a valid number...");
                in.next();
            }
        } while (input < 1 || input > 5);
        return input;
    }

    public static void handleChoice(int choice, DocumentDAO documentDAO, BookDAO bookDAO, MagazineDAO magazineDAO,
            ScientificJournalDAO scientificJournalDAO, UniversityThesisDAO universityThesisDAO) {
        switch (choice) {
            case 1:
                do {
                    List<Book> books = bookDAO.getAll();
                    Book book = BookInterface.BookList(books);
                    if (book == null)
                        break;
                    book.showDetails();
                    int input = DocumentManagementMenu(reservationDAO.isReserved(book));

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
                            UserInterface.StudentList(students);

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
                } while (true);
            case 2:
                // List magazines
                break;
            case 3:
                // List journals
                break;
            case 4:
                // List thesis
                break;
            case 5:
                break;
            default:
                break;
        }
    }

    public static int DocumentManagementMenu(Boolean isReserved) {
        int input = -1;
        int max = 4;
        if (isReserved)
            max = 3;
        do {

            System.out.println("\t\t+----------------------------------------+");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t|     1- Update Document                 |");
            System.out.println("\t\t|     2- Delete Document                 |");
            if (isReserved) {
                System.out.println("\t\t|     3- Back                            |");

            } else {
                System.out.println("\t\t|     3- Reserve Document                |");
                System.out.println("\t\t|     4- Back                            |");
            }

            System.out.println("\t\t|                                        |");
            System.out.println("\t\t+----------------------------------------+");
            System.out.print("Pick your choice : ");

            try {
                input = in.nextInt();
                if (input < 1 || input > max) {
                    System.out.println("Please pick a choice between 1 and " + max + "...");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Please pick a valid number...");
                in.next();
            }

        } while (input < 1 || input > max);
        return input;
    }
}
