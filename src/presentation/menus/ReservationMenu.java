package src.presentation.menus;

import java.util.List;
import java.util.Scanner;

import src.business.Reservation;
import src.dao.interfaces.ReservationDAO;
import src.presentation.reservation.ReservationUI;
import src.presentation.interfaces.Menu;
import src.services.reservation.ReservationDAOImpl;

public class ReservationMenu implements Menu {
    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Override
    public void display() {
        System.out.println("\n\t\t+----------------------------------------+");
        System.out.println("\t\t|            RESERVATION MENU            |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t|     1- List all reservations           |");
        System.out.println("\t\t|     2- List all borrowed documents     |");
        System.out.println("\t\t|     3- Back                            |");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.print("Pick your choice : ");
    }

    @Override
    public int getChoice() {
        int input = -1;
        try {
            input = in.nextInt();
            if (input < 1 || input > 3) {
                System.out.println("Please pick a choice between 1 and 3...");
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
        switch (choice) {
            case 1:
                do {
                    List<Reservation> reservations = reservationDAO.getAll();
                    Reservation selectedReservation = ReservationUI.reservationList(reservations);
                    if (selectedReservation == null)
                        break;
                    selectedReservation.showReservationDetails();
                    int input = ReservationUI.ReservationManagementMenu();
                    ReservationUI.handleReservation(input, selectedReservation, reservationDAO);

                } while (true);
                break;
            case 2:
                do {
                    List<Reservation> reservations = reservationDAO.getAllBorrowed();
                    Reservation selectedReservation = ReservationUI.reservationList(reservations);
                    if (selectedReservation == null)
                        break;
                    selectedReservation.showReservationDetails();
                    int input = ReservationUI.BorrowingManagementMenu();
                    ReservationUI.handleBorrowable(input, selectedReservation, reservationDAO);

                } while (true);
                break;
            default:
                break;
        }
    }

}
