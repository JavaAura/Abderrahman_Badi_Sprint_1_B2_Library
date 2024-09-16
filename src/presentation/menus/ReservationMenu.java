package src.presentation.menus;

import java.util.Scanner;

import src.presentation.interfaces.Menu;

public class ReservationMenu implements Menu {
    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

  

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
                // all reservations
                break;
            case 2:
                // all borrowed docs
                break;
            default:
                break;
        }
    }
}
