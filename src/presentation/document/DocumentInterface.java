package src.presentation.document;

import java.util.List;
import java.util.Scanner;

import src.business.Book;
import src.business.Document;
import src.business.Magazine;
import src.business.ScientificJournal;
import src.business.UniversityThesis;
import src.dao.interfaces.BookDAO;
import src.dao.interfaces.DocumentDAO;
import src.dao.interfaces.MagazineDAO;
import src.dao.interfaces.ReservationDAO;
import src.dao.interfaces.ScientificJournalDAO;
import src.dao.interfaces.UniversityThesisDAO;
import src.services.reservation.ReservationDAOImpl;

public class DocumentInterface {

    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

    static ReservationDAO reservationDAO = new ReservationDAOImpl();

    public static int documentList(List<Document> documents) {
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
                    Book book = BookInterface.bookList(books);
                    if (book == null)
                        break;
                    book.showDetails();
                    int input = documentManagementMenu(reservationDAO.isReserved(book));
                    BookInterface.handleChoice(input, book, documentDAO, bookDAO);

                } while (true);
            case 2:
                do {
                    List<Magazine> magazines = magazineDAO.getAll();
                    Magazine magazine = MagazineInterface.magazineList(magazines);
                    if (magazine == null)
                        break;
                    magazine.showDetails();
                    int input = documentManagementMenu(reservationDAO.isReserved(magazine));
                    MagazineInterface.handleChoice(input, magazine, documentDAO, magazineDAO);
                } while (true);
                break;
            case 3:
                do {
                    List<ScientificJournal> journals = scientificJournalDAO.getAll();
                    ScientificJournal journal = JournalInterface.journalList(journals);
                    if (journal == null)
                        break;
                    journal.showDetails();
                    int input = documentManagementMenu(reservationDAO.isReserved(journal));
                    JournalInterface.handleChoice(input, journal, documentDAO, scientificJournalDAO);
                } while (true);
                break;
            case 4:
                do {
                    List<UniversityThesis> thesis = universityThesisDAO.getAll();
                    UniversityThesis uniThesis = ThesisInterface.thesisList(thesis);
                    if (uniThesis == null)
                        break;
                    uniThesis.showDetails();
                    int input = documentManagementMenu(reservationDAO.isReserved(uniThesis));
                    ThesisInterface.handleChoice(input, uniThesis, documentDAO, universityThesisDAO);
                } while (true);
                break;
            default:
                break;
        }
    }

    public static int documentManagementMenu(Boolean isReserved) {
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
