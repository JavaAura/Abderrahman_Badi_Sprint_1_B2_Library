package src.presentation;

import src.presentation.interfaces.Menu;
import src.presentation.menus.DocumentMenu;
import src.presentation.menus.MainMenu;
import src.presentation.menus.UserMenu;

public class ConsoleUI {

    public ConsoleUI() {

    }

    // ------------ Menu loop --------------
    
    public void start() {
        int choice;
        Menu userMenu = new UserMenu();
        Menu documentMenu = new DocumentMenu();
        Menu mainMenu = new MainMenu(userMenu, documentMenu);

        do {
            mainMenu.display();
            choice = mainMenu.getChoice();
            mainMenu.handleChoice(choice);
        } while (choice != 3);
    }

}
