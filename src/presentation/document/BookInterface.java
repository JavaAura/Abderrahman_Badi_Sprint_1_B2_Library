package src.presentation.document;

import java.util.List;
import java.util.Scanner;

import src.business.Book;

public class BookInterface {
    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

    public static Book BookList(List<Book> books) {
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

                break; // automatically goes back to last Menu
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
                    break;
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
}
