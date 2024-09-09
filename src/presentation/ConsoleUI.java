package src.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.business.Book;
import src.business.Document;
import src.business.Library;
import src.business.Magazine;
import src.utils.DateUtils;
import src.utils.Filter;
import src.utils.InputValidator;

public class ConsoleUI {

    Library lib;
    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    int input = -1;

    public ConsoleUI() {
        this.lib = new Library();
        lib.seedLibrary();
    }

    // ------------ Menu loop --------------
    public void start() {
        int input;
        do {
            input = showMenu();
            menuHandler(input);
        } while (input != 6);
    }

    public int showMenu() {

        do {
            System.out.println("\n\t\t+----------------------------------------+");
            System.out.println("\t\t|            MENU PRINCIPALE             |");
            System.out.println("\t\t+----------------------------------------+");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t|     1- Add a document                  |");
            System.out.println("\t\t|     2- Borrow a document               |");
            System.out.println("\t\t|     3- Return a document               |");
            System.out.println("\t\t|     4- Show all documents              |");
            System.out.println("\t\t|     5- Find a document                 |");
            System.out.println("\t\t|     6- Exit                            |");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t+----------------------------------------+");
            System.out.print("Pick your choice : ");

            try {
                input = in.nextInt();
                if (input < 1 || input > 6) {
                    System.out.print("Please pick a choice between 1 and 6...");
                    in.next();
                }
            } catch (Exception e) {
                System.out.print("Please pick a valid number...");
                in.next();
                in.next();
            }

        } while (input < 1 || input > 6);
        return input;
    }

    public void menuHandler(int input) {
        switch (input) {
            case 1:
                addDocumentUI();
                break;
            case 2:
                borrowDocumentUI();
                break;
            case 3:
                returnDocumentUI();
                break;
            case 4:
                listDocumentsUI(Filter.ALL);
                System.out.print("Press Enter key to continue...");
                in.next();
                break;
            case 5:
                findDocumentUI();
                break;
            default:
                return;
        }
    }

    private void addDocumentUI() {
        Document doc;
        LocalDate publicationDate = null;

        String message = "What would you like to add ?\n 1- Book \t\t 2- Magazine \n";

        input = InputValidator.promptAndParseInt(message, 1, 2);

        String title = InputValidator.promptAndParseString("Enter book title: ");

        String author = InputValidator.promptAndParseString("Enter author name: ");

        int pageNumbers = InputValidator.promptAndParseInt("Enter number of pages: ");

        // ---------- Date validation ----------------
        while (publicationDate == null) {
            try {
                System.out.print("Enter publication date (dd-MM-yyyy): ");
                String dateString = in.next();

                // Parse and check if the date is valid (pattern)
                publicationDate = DateUtils.parseDate(dateString);
            } catch (DateTimeParseException e) {
                System.out.print("Invalid date format. Please enter the date in the format dd-MM-yyyy...");
                in.next();
            } catch (Exception e) {
                System.out.print(e.getMessage());
                in.next();
            }
        }

        if (input == 1) {
            System.out.print("Enter book number: ");
            int number = in.nextInt();
            doc = new Book(title, author, publicationDate, pageNumbers, number);
        } else {
            System.out.print("Enter ISBN number: ");
            int number = in.nextInt();
            doc = new Magazine(title, author, publicationDate, pageNumbers, number);
        }

        lib.addDocument(doc);

        System.out.print("Press Enter key to continue...");
        in.next();
    }

    private void borrowDocumentUI() {
        // in.useDelimiter(System.lineSeparator());
        List<Document> filteredDocuments = listDocumentsUI(Filter.AVAILABLE);

        if (filteredDocuments.isEmpty()) {
            System.out.println("No available documents to borrow.");
            return; // Exit if no documents are available
        }

        int[] documentIds = filteredDocuments.stream()
                .mapToInt(Document::getId)
                .toArray();

        do {
            System.out.print("Choose a document to borrow: ");
            try {
                input = in.nextInt();
                if (!Arrays.stream(documentIds).anyMatch(id -> id == input)) {
                    System.out.print("Please pick a valid Id...");
                    in.next();
                }
            } catch (Exception e) {
                System.out.print("Please pick a valid number...");
                in.next();
                in.next();
            }
        } while (!Arrays.stream(documentIds).anyMatch(id -> id == input));

        // Get the selected doc by its id
        Document selectedDoc = filteredDocuments.stream().filter(doc -> doc.getId() == input).findFirst().get();
        lib.borrowDocument(selectedDoc);
        System.out.print("Document been borrowed successfully ");
        in.next();
    }

    private void returnDocumentUI() {
        List<Document> filteredDocuments = listDocumentsUI(Filter.BORROWED);
        int[] documentIds = filteredDocuments.stream()
                .mapToInt(Document::getId)
                .toArray();

        do {
            System.out.print("Choose a document to return: ");
            try {
                input = in.nextInt();
                if (!Arrays.stream(documentIds).anyMatch(id -> id == input)) {
                    System.out.print("Please pick a valid Id...");
                    in.next();
                }
            } catch (Exception e) {
                System.out.print("Please pick a valid number...");
                in.next();
                in.next();
            }
        } while (!Arrays.stream(documentIds).anyMatch(id -> id == input));

        // Get the selected doc by its id
        Document selectedDoc = filteredDocuments.stream().filter(doc -> doc.getId() == input).findFirst().get();
        lib.borrowDocument(selectedDoc);
        System.out.print("Document been returned successfully ");
        in.next();
    }

    public List<Document> listDocumentsUI(Filter filter) {
        List<Document> filteredDocuments = new ArrayList<>();

        switch (filter) {
            case ALL:
                filteredDocuments.addAll(lib.getDocuments());
                break;
            case AVAILABLE:
                for (Document doc : lib.getDocuments()) {
                    if (!doc.getIsBorrowed()) { // Check if the document is not borrowed
                        filteredDocuments.add(doc);
                    }
                }
                break;
            case BORROWED:
                for (Document doc : lib.getDocuments()) {
                    if (doc.getIsBorrowed()) { // Check if the document is borrowed
                        filteredDocuments.add(doc);
                    }
                }
                break;
            default:
                break;

        }

        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("| Id |        Title        |         Author        |        Published        |  Pages  |");
        System.out.println("+--------------------------------------------------------------------------------------+");
        for (Document document : filteredDocuments) {
            System.out.printf("| %-3d| %-20s| %-22s| %-24s| %-8d|%n", document.getId(), document.getTitle(),
                    document.getAuthor(),
                    document.getPublicationDate(), document.getPageNumbers());
            System.out.println(
                    "+--------------------------------------------------------------------------------------+");
        }

        return filteredDocuments;
    }

    private void findDocumentUI() {

        Map<String, Document> documents = lib.getDocumentMap();
        // in.useDelimiter(System.lineSeparator());

        System.out.print("Enter the title of the document: ");
        String input = in.next();

        Document document = documents.get(input);

        if (document != null) {
            document.showDetails();
            in.next();
        } else {
            System.out.print("No such document in our library...");
            in.next();
        }
    }

    public int showDetailsUI() {

        do {
            System.out.println("\t\t+----------------------------------------+");
            System.out.println("\t\t|     1- Borrow the document             |");
            System.out.println("\t\t|     2- Return the document             |");
            System.out.println("\t\t|     3- Back                            |");
            System.out.println("\t\t+----------------------------------------+");
            System.out.print("\t\t Pick your choice : ");

            try {
                input = in.nextInt();
                if (input < 1 || input > 3) {
                    System.out.print("Please pick a choice between 1 and 3...");
                    in.nextLine();
                }
            } catch (Exception e) {
                System.out.print("Please pick a valid number...");
                in.nextLine();
            }

        } while (input < 1 || input > 3);

        in.close();

        return input;
    }

}
