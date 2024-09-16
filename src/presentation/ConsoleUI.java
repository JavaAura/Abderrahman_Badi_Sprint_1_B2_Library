package src.presentation;

import src.presentation.interfaces.Menu;
import src.presentation.menus.DocumentMenu;
import src.presentation.menus.MainMenu;
import src.presentation.menus.ReservationMenu;
import src.presentation.menus.UserMenu;

public class ConsoleUI {

    public ConsoleUI() {

    }

    // ------------ Menu loop --------------
    
    public void start() {
        int choice;
        Menu userMenu = new UserMenu();
        Menu documentMenu = new DocumentMenu();
        Menu reservatioMenu = new ReservationMenu();
        Menu mainMenu = new MainMenu(userMenu, documentMenu, reservatioMenu);

        do {
            mainMenu.display();
            choice = mainMenu.getChoice();
            mainMenu.handleChoice(choice);
        } while (choice != 4);
    }

}
