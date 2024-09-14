package src.presentation.menus;

import java.util.Scanner;

import src.presentation.interfaces.Menu;

public class DocumentMenu implements Menu {
    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

    @Override
    public void display() {
        System.out.println("\n\t\t+----------------------------------------+");
        System.out.println("\t\t|             DOCUMENTS MENU             |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t|     1- List All Documents              |");
        System.out.println("\t\t|     2- Add a Document                  |");
        System.out.println("\t\t|     3- Manage Reservations             |");
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
        switch (choice) {
            case 1:
                // List All Documents
                break;
            case 2:
                // Add a Document
                break;
            case 3:
                // Manage Reservations
                break;
            case 4:
                break;
            default:
                break;
        }
    }
}
