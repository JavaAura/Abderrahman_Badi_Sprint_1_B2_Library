package src.presentation.menus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import src.business.Document;
import src.business.Book;
import src.business.Magazine;
import src.business.ScientificJournal;
import src.business.UniversityThesis;

import src.dao.interfaces.BookDAO;
import src.dao.interfaces.DocumentDAO;
import src.dao.interfaces.MagazineDAO;
import src.dao.interfaces.ScientificJournalDAO;
import src.dao.interfaces.UniversityThesisDAO;
import src.presentation.document.DocumentUI;
import src.presentation.interfaces.Menu;
import src.services.document.BookDAOImpl;
import src.services.document.DocumentDAOImpl;
import src.services.document.MagazineDAOImpl;
import src.services.document.ScientificJournalDAOImpl;
import src.services.document.UniversityThesisDAOImpl;
import src.utils.InputValidator;

public class DocumentMenu implements Menu {
    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

    DocumentDAO documentDAO = new DocumentDAOImpl();
    BookDAO bookDAO = new BookDAOImpl();
    MagazineDAO magazineDAO = new MagazineDAOImpl();
    ScientificJournalDAO scientificJournalDAO = new ScientificJournalDAOImpl();
    UniversityThesisDAO universityThesisDAO = new UniversityThesisDAOImpl();

    @Override
    public void display() {
        System.out.println("\n\t\t+----------------------------------------+");
        System.out.println("\t\t|             DOCUMENTS MENU             |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t|     1- List All Documents              |");
        System.out.println("\t\t|     2- Add a Document                  |");
        System.out.println("\t\t|     3- Find Document                   |");
        System.out.println("\t\t|     4- Back                            |");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.print("Pick your choice : ");
    }

    @Override
    public int getChoice() {
        int input = -1;
        try {
            input = in.nextInt();
            if (input < 1 || input > 4) {
                System.out.println("Please pick a choice between 1 and 4...");
                in.next();
            }
        } catch (Exception e) {
            System.out.println("Please pick a valid number...");
            in.next();
        }
        return input;
    }

    @Override
    public void handleChoice(int choice) {
        int input;
        switch (choice) {
            case 1:
                List<Document> documents = documentDAO.getAll();
                do {
                    input = DocumentUI.documentList(documents, false);
                    DocumentUI.handleChoice(input, documentDAO, bookDAO, magazineDAO, scientificJournalDAO,
                            universityThesisDAO);
                } while (input != 5);
                break;
            case 2:
                int selectedChoice = InputValidator
                        .promptAndParseInt(
                                "What would you like to add ?\n 1- Book \t\t 2- Magazine\n 3- Scientific journal\t 4- University thesis\nPick your choice : ",
                                1,
                                4);
                String title = InputValidator.promptAndParseString("Document title : ");
                String author = InputValidator.promptAndParseString("Document's author : ");
                int pageNumbers = InputValidator.promptAndParseInt("Document total page number : ");
                LocalDate publicationDate = InputValidator.promptAndParseDate("Document's publication date : ");
                switch (selectedChoice) {
                    case 1:
                        int number = InputValidator.promptAndParseInt("Book's number :");
                        Book book = new Book(title, author, publicationDate, pageNumbers, number);
                        bookDAO.save(book);
                        break;
                    case 2:
                        int isbn = InputValidator.promptAndParseInt("Magazine's isbn :");
                        Magazine magazine = new Magazine(title, author, publicationDate, pageNumbers, isbn);
                        magazineDAO.save(magazine);
                        break;
                    case 3:
                        String journalField = InputValidator.promptAndParseString("Field : ");
                        ScientificJournal scientificJournal = new ScientificJournal(title, author, publicationDate,
                                pageNumbers, journalField);
                        scientificJournalDAO.save(scientificJournal);
                        break;
                    case 4:
                        String thesisField = InputValidator.promptAndParseString("Field : ");
                        UniversityThesis universityThesis = new UniversityThesis(title, author, publicationDate,
                                pageNumbers, thesisField);
                        universityThesisDAO.save(universityThesis);
                        break;
                    default:
                        break;
                }

                in.next();
                break;
            case 3:
                String userInput = InputValidator.promptAndParseString("Search (title, author, ...) : ");
                List<Document> searchedDocuments = documentDAO.findDocument(userInput);
                input = DocumentUI.documentList(searchedDocuments, true);
                in.next();
                break;
            default:
                break;
        }
    }
}
