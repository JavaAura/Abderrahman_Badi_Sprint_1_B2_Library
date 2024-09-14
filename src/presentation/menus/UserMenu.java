package src.presentation.menus;

import java.util.List;
import java.util.Scanner;

import src.business.Professor;
import src.business.Student;
import src.business.User;
import src.dao.interfaces.ProfessorDAO;
import src.dao.interfaces.StudentDAO;
import src.dao.interfaces.UserDAO;
import src.presentation.interfaces.Menu;
import src.presentation.user.UserInterfaces;
import src.services.user.ProfessorDAOImpl;
import src.services.user.StudentDAOImpl;
import src.services.user.UserDAOImpl;
import src.utils.InputValidator;

public class UserMenu implements Menu {

    UserDAO userDAO = new UserDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    ProfessorDAO professorDAO = new ProfessorDAOImpl();

    Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());
    int selectedId = -1;

    @Override
    public void display() {
        System.out.println("\n\t\t+----------------------------------------+");
        System.out.println("\t\t|               USERS MENU               |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t|     1- List All Users                  |");
        System.out.println("\t\t|     2- List All Students               |");
        System.out.println("\t\t|     3- List All Professors             |");
        System.out.println("\t\t|     4- Add a User                      |");
        System.out.println("\t\t|     5- Back                            |");
        System.out.println("\t\t|                                        |");
        System.out.println("\t\t+----------------------------------------+");
        System.out.print("Pick your choice : ");
    }

    @Override
    public int getChoice() {
        int input = -1;
        try {
            input = in.nextInt();
            if (input < 1 || input > 5) {
                System.out.println("Please pick a choice between 1 and 5...");
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
                List<User> users = userDAO.getAll();
                UserInterfaces.UserList(users);
                in.next();
                break;
            case 2:

                List<Student> students = studentDAO.getAll();
                UserInterfaces.StudentList(students);

                Student selectedStudent = null;

                do {
                    System.out.print(
                            "0 - Return to User Menu \nPlease pick a user by entering their ID \nPick your choice : ");
                    try {
                        selectedId = in.nextInt();
                        if (selectedId == 0)
                            break;
                        for (Student student : students) {
                            if (student.getId() == selectedId) {
                                selectedStudent = student;
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

                UserInterfaces.ShowDetails(selectedStudent, userDAO, studentDAO, professorDAO);

                break;
            case 3:

                List<Professor> professors = professorDAO.getAll();
                UserInterfaces.ProfessorList(professors);

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

                UserInterfaces.ShowDetails(selectedProfessor, userDAO, studentDAO, professorDAO);

                break;
            case 4:
                int selectedChoice = InputValidator
                        .promptAndParseInt(
                                "What would you like to add ?\n 1- Student \t\t 2- Professor\nPick your choice : ", 1,
                                2);
                String name = InputValidator.promptAndParseString("Name : ");
                String lastName = InputValidator.promptAndParseString("Last Name : ");
                String registrationNumber = InputValidator.promptAndParseString("Registration Number : ");
                if (selectedChoice == 1) {
                    String major = InputValidator.promptAndParseString("Major : ");
                    String grade = InputValidator.promptAndParseString("Class : ");

                    Student newStudent = new Student(name, lastName, registrationNumber, major, grade);
                    studentDAO.save(newStudent);
                } else {
                    String department = InputValidator.promptAndParseString("Department : ");
                    Professor newProfessor = new Professor(name, lastName, registrationNumber, department);
                    professorDAO.save(newProfessor);
                }

                in.next();
                break;
            case 5:
                break;
            default:
                break;
        }
    }
}
