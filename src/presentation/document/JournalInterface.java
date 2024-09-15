package src.presentation.document;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import src.utils.InputValidator;

import src.business.ScientificJournal;
import src.business.Professor;

import src.dao.interfaces.ScientificJournalDAO;
import src.dao.interfaces.DocumentDAO;
import src.dao.interfaces.ReservationDAO;
import src.dao.interfaces.ProfessorDAO;

import src.services.DocumentService;
import src.services.reservation.ReservationDAOImpl;
import src.services.user.ProfessorDAOImpl;

import src.presentation.user.UserInterface;

public class JournalInterface {
    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    static ProfessorDAO professorDAO = new ProfessorDAOImpl();
    static ReservationDAO reservationDAO = new ReservationDAOImpl();
    static DocumentService documentService = new DocumentService(reservationDAO);

    static int selectedId = -1;

    public static ScientificJournal journalList(List<ScientificJournal> journals) {
        ScientificJournal selectedJournal = null;
        int selectedId;
        do {
            if (journals.size() == 0) {
                System.out.println(
                        "+--------------------------------------------------------------------------------------+");
                System.out.println(
                        "|                                  No journals found                                  |");
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
            for (ScientificJournal journal : journals) {
                System.out.printf("| %-3d| %-24s | %-24s | %-9d |    %-10s    | %-12s |\n", journal.getId(),
                        journal.getTitle(),
                        journal.getAuthor(),
                        journal.getPageNumbers(),
                        journal.getPublicationDate(),
                        journal.getField());
                System.out.println(
                        "+--------------------------------------------------------------------------------------------------------+");
            }

            System.out.print(
                    "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
            try {
                selectedId = in.nextInt();
                if (selectedId == 0)
                    break;
                for (ScientificJournal journal : journals) {
                    if (journal.getId() == selectedId) {
                        selectedJournal = journal;
                        break;
                    }
                }
                if (selectedJournal == null) {
                    System.out.println("Invalid ID. Please try again.");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                in.next();
            }
        } while (selectedJournal == null);
        return selectedJournal;
    }

    public static void handleChoice(int input, ScientificJournal journal, DocumentDAO documentDAO, ScientificJournalDAO magazineDAO) {
        switch (input) {
            case 1:
                String title = InputValidator.promptAndParseNullableString("Title : ");
                String author = InputValidator.promptAndParseNullableString("Author : ");
                LocalDate publicationDate = InputValidator
                        .promptAndParseNullableDate("Publication date : ");
                Integer pageNumbers = InputValidator.promptAndParseNullableInt("Page numbers : ");
                String field = InputValidator.promptAndParseNullableString("Field : ");

                magazineDAO.update(journal,
                        new String[] { title, author,
                                (publicationDate != null) ? publicationDate.toString() : null,
                                (pageNumbers != null) ? pageNumbers.toString() : null,
                                field });
                break;
            case 2:
                documentDAO.delete(journal);
                break;
            case 3:
                if (reservationDAO.isReserved(journal))
                    break;
                List<Professor> professors = professorDAO.getAll();
                UserInterface.ProfessorList(professors);

                Professor selectedStudent = null;

                do {
                    System.out.print(
                            "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
                    try {
                        selectedId = in.nextInt();
                        if (selectedId == 0)
                            break;
                        for (Professor professor : professors) {
                            if (professor.getId() == selectedId) {
                                selectedStudent = professor;
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

                documentService.reserveDocument(journal, selectedStudent);
                break;
            default:
                break;
        }
    }
}
