package src.presentation.document;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import src.utils.InputValidator;

import src.business.Magazine;
import src.business.Student;

import src.dao.interfaces.MagazineDAO;
import src.dao.interfaces.DocumentDAO;
import src.dao.interfaces.ReservationDAO;
import src.dao.interfaces.StudentDAO;

import src.services.DocumentService;
import src.services.reservation.ReservationDAOImpl;
import src.services.user.StudentDAOImpl;

import src.presentation.user.UserUI;

public class MagazineUI {
    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    static StudentDAO studentDAO = new StudentDAOImpl();
    static ReservationDAO reservationDAO = new ReservationDAOImpl();
    static DocumentService documentService = new DocumentService(reservationDAO);

    static int selectedId = -1;

    public static Magazine magazineList(List<Magazine> magazines) {
        Magazine selectedMagazine = null;
        int selectedId;
        do {
            if (magazines.size() == 0) {
                System.out.println(
                        "+--------------------------------------------------------------------------------------+");
                System.out.println(
                        "|                                  No magazines found                                  |");
                System.out.println(
                        "+--------------------------------------------------------------------------------------+\n\n\n");

                break; // automatically goes back to last Menu
            }

            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "| Id |           Title          |          Author          |   Pages   | Publication Date |     isbn    |");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------+");
            for (Magazine magazine : magazines) {
                System.out.printf("| %-3d| %-24s | %-24s | %-9d |    %-10s    | %-11d |\n", magazine.getId(),
                        magazine.getTitle(),
                        magazine.getAuthor(),
                        magazine.getPageNumbers(),
                        magazine.getPublicationDate(),
                        magazine.getIsbn());
                System.out.println(
                        "+-------------------------------------------------------------------------------------------------------+");
            }

            System.out.print(
                    "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
            try {
                selectedId = in.nextInt();
                if (selectedId == 0)
                    break;
                for (Magazine magazine : magazines) {
                    if (magazine.getId() == selectedId) {
                        selectedMagazine = magazine;
                        break;
                    }
                }
                if (selectedMagazine == null) {
                    System.out.println("Invalid ID. Please try again.");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                in.next();
            }
        } while (selectedMagazine == null);
        return selectedMagazine;
    }

    public static void handleChoice(int input, Magazine magazine, DocumentDAO documentDAO, MagazineDAO magazineDAO) {
        switch (input) {
            case 1:
                String title = InputValidator.promptAndParseNullableString("Title : ");
                String author = InputValidator.promptAndParseNullableString("Author : ");
                LocalDate publicationDate = InputValidator
                        .promptAndParseNullableDate("Publication date : ");
                Integer pageNumbers = InputValidator.promptAndParseNullableInt("Page numbers : ");
                Integer isbn = InputValidator.promptAndParseNullableInt("isbn : ");

                magazineDAO.update(magazine,
                        new String[] { title, author,
                                (publicationDate != null) ? publicationDate.toString() : null,
                                (pageNumbers != null) ? pageNumbers.toString() : null,
                                (isbn != null) ? isbn.toString() : null });
                break;
            case 2:
                documentDAO.delete(magazine);
                break;
            case 3:
                if (reservationDAO.isReserved(magazine))
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

                documentService.reserveDocument(magazine, selectedStudent);
                break;
            default:
                break;
        }
    }
}
