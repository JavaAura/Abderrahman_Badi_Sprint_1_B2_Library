package src.presentation.menus;

import java.util.Scanner;

import src.presentation.interfaces.Menu;

public class UserMenu implements Menu {
    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

    @Override
    public void display() {
        System.out.println("\n\t\t+----------------------------------------+");
        System.out.println("\t\t|               USERS MENU               |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t|     1- List All Users                  |");
        System.out.println("\t\t|     2- Add a User                      |");
        System.out.println("\t\t|     3- Back                            |");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.print("Pick your choice : ");
    }

    @Override
    public int getChoice() {
        int input;
        do {
            try {
                input = in.nextInt();
                if (input < 1 || input > 3) {
                    System.out.println("Please pick a choice between 1 and 3...");
                    in.next();
                }
            } catch (Exception e) {
                System.out.println("Please pick a valid number...");
                in.next();
                input = 0;
            }
        } while (input < 1 || input > 3);
        return input;
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                // List All Users
                break;
            case 2:
                // Add a User
                break;
            case 3:
                // Back to Main Menu
                break;
            default:
                break;
        }
    }
}
