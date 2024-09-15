package src.presentation.document;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import src.utils.InputValidator;

import src.business.UniversityThesis;
import src.business.Professor;

import src.dao.interfaces.UniversityThesisDAO;
import src.dao.interfaces.DocumentDAO;
import src.dao.interfaces.ReservationDAO;
import src.dao.interfaces.ProfessorDAO;

import src.services.DocumentService;
import src.services.reservation.ReservationDAOImpl;
import src.services.user.ProfessorDAOImpl;

import src.presentation.user.UserInterface;

public class ThesisInterface {
    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    static ProfessorDAO professorDAO = new ProfessorDAOImpl();
    static ReservationDAO reservationDAO = new ReservationDAOImpl();
    static DocumentService documentService = new DocumentService(reservationDAO);

    static int selectedId = -1;

    public static UniversityThesis thesisList(List<UniversityThesis> thesis) {
        UniversityThesis selectedThesis = null;
        int selectedId;
        do {
            if (thesis.size() == 0) {
                System.out.println(
                        "+--------------------------------------------------------------------------------------+");
                System.out.println(
                        "|                                    No thesis found                                   |");
                System.out.println(
                        "+--------------------------------------------------------------------------------------+\n\n\n");

                break; // automatically goes back to last Menu
            }

            System.out.println(
                    "+--------------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "| Id |           Title          |          Author          |   Pages   | Publication Date |     Field    |");
            System.out.println(
                    "+--------------------------------------------------------------------------------------------------------+");
            for (UniversityThesis uniThesis : thesis) {
                System.out.printf("| %-3d| %-24s | %-24s | %-9d |    %-10s    | %-12s |\n", uniThesis.getId(),
                        uniThesis.getTitle(),
                        uniThesis.getAuthor(),
                        uniThesis.getPageNumbers(),
                        uniThesis.getPublicationDate(),
                        uniThesis.getField());
                System.out.println(
                        "+--------------------------------------------------------------------------------------------------------+");
            }

            System.out.print(
                    "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
            try {
                selectedId = in.nextInt();
                if (selectedId == 0)
                    break;
                for (UniversityThesis uniThesis : thesis) {
                    if (uniThesis.getId() == selectedId) {
                        selectedThesis = uniThesis;
                        break;
                    }
                }
                if (selectedThesis == null) {
                    System.out.println("Invalid ID. Please try again.");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                in.next();
            }
        } while (selectedThesis == null);
        return selectedThesis;
    }

    public static void handleChoice(int input, UniversityThesis uniThesis, DocumentDAO documentDAO, UniversityThesisDAO universityThesisDAO) {
        switch (input) {
            case 1:
                String title = InputValidator.promptAndParseNullableString("Title : ");
                String author = InputValidator.promptAndParseNullableString("Author : ");
                LocalDate publicationDate = InputValidator
                        .promptAndParseNullableDate("Publication date : ");
                Integer pageNumbers = InputValidator.promptAndParseNullableInt("Page numbers : ");
                String field = InputValidator.promptAndParseNullableString("Field : ");

                universityThesisDAO.update(uniThesis,
                        new String[] { title, author,
                                (publicationDate != null) ? publicationDate.toString() : null,
                                (pageNumbers != null) ? pageNumbers.toString() : null,
                                field });
                break;
            case 2:
                documentDAO.delete(uniThesis);
                break;
            case 3:
                if (reservationDAO.isReserved(uniThesis))
                    break;
                List<Professor> professors = professorDAO.getAll();
                UserInterface.ProfessorList(professors);

                Professor selectedProfessor = null;

                do {
                    System.out.print(
                            "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
                    try {
                        selectedId = in.nextInt();
                        if (selectedId == 0)
                            break;
                        for (Professor professor : professors) {
                            if (professor.getId() == selectedId) {
                                selectedProfessor = professor;
                                break;
                            }
                        }
                        if (selectedProfessor == null) {
                            System.out.println("Invalid ID. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        in.next();
                    }
                } while (selectedProfessor == null);

                if (selectedProfessor == null)
                    break;

                documentService.reserveDocument(uniThesis, selectedProfessor);
                break;
            default:
                break;
        }
    }
}
