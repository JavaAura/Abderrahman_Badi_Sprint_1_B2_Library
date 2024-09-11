package src.presentation;

import java.util.Scanner;

public class ConsoleUI {

    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    int input = -1;

    public ConsoleUI() {
    }

    // ------------ Menu loop --------------
    public void start() {
        int input;
        do {
            input = showMenu();
            menuHandler(input);
        } while (input != 3);
    }

    public int showMenu() {

        do {
            System.out.println("\n\t\t+----------------------------------------+");
            System.out.println("\t\t|            MENU PRINCIPALE             |");
            System.out.println("\t\t+----------------------------------------+");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t|     1- Manage Users                    |");
            System.out.println("\t\t|     2- Manage Documents                |");
            System.out.println("\t\t|     3- Exit                            |");
            System.out.println("\t\t|                                        |");
            System.out.println("\t\t+----------------------------------------+");
            System.out.print("Pick your choice : ");

            try {
                input = in.nextInt();
                if (input < 1 || input > 3) {
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
                // addDocumentUI();
                break;
            case 2:
                // borrowDocumentUI();
                break;
            case 3:
                // returnDocumentUI();
                break;
            case 4:
                // listDocumentsUI(Filter.ALL);
                System.out.print("Press Enter key to continue...");
                in.next();
                break;
            case 5:
                // findDocumentUI();
                break;
            default:
                return;
        }
    }

}
