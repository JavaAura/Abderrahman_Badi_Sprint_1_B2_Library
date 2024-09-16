package src.presentation.reservation;

import java.util.List;
import java.util.Scanner;

import src.business.Reservation;
import src.dao.interfaces.ReservationDAO;

public class ReservationUI {

    public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

    public static Reservation reservationList(List<Reservation> reservations) {
        Reservation selectedReservation = null;
        int selectedId;
        do {
            if (reservations.size() == 0) {
                System.out.println(
                        "+--------------------------------------------------------------------------------------+");
                System.out.println(
                        "|                                  No reservations found                               |");
                System.out.println(
                        "+--------------------------------------------------------------------------------------+\n\n\n");
                return selectedReservation; // automatically goes back to Documents Menu
            }

            System.out.println(
                    "+--------------------------------------------------------------------------------------------------+");
            System.out.println(
                    "| Id |         Reservor         |          Document        | Reservation Date | Reservation Status |");
            System.out.println(
                    "+--------------------------------------------------------------------------------------------------+");
            for (Reservation reservation : reservations) {
                System.out.printf("| %-3d| %-24s | %-24s | %-16s | %-18s |\n",
                        reservation.getId(),
                        reservation.getUser().getName() + " " + reservation.getUser().getLastName(),
                        reservation.getDocument().getTitle(),
                        reservation.getReservationDate(),
                        reservation.getReservationStatus().toString());
                System.out.println(
                        "+--------------------------------------------------------------------------------------------------+");
            }

            System.out.print(
                    "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
            try {
                selectedId = in.nextInt();
                if (selectedId == 0)
                    return selectedReservation;
                for (Reservation reservation : reservations) {
                    if (reservation.getId() == selectedId) {
                        selectedReservation = reservation;
                        break;
                    }
                }
                if (selectedReservation == null) {
                    System.out.println("Invalid ID. Please try again.");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                in.next();
            }
        } while (selectedReservation == null);
        return selectedReservation;

    }

    public static int ReservationManagementMenu() {
        int input = -1;
        int max = 3;
        do {
            System.out.println("\t\t+----------------------------------------+");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t|     1- Mark reservation as borrowed    |");
            System.out.println("\t\t|     2- Cancel reservation              |");
            System.out.println("\t\t|     3- Back                            |");
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

    public static void handleReservation(int choice, Reservation reservation, ReservationDAO reservationDAO) {
        switch (choice) {
            case 1:
                reservationDAO.borrowDocument(reservation);
                break;
            case 2:
                reservationDAO.cancelReservation(reservation);
                break;
            default:
                break;
        }
    }

    public static int BorrowingManagementMenu() {
        int input = -1;
        int max = 2;
        do {
            System.out.println("\t\t+----------------------------------------+");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t|     1- Mark document as returned       |");
            System.out.println("\t\t|     2- Back                            |");
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

    public static void handleBorrowable(int choice, Reservation reservation, ReservationDAO reservationDAO) {
        switch (choice) {
            case 1:
                reservationDAO.returnDocument(reservation);
                break;
            default:
                break;
        }
    }


   
}
