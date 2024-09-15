package src.presentation.user;

import java.util.List;
import java.util.Scanner;

import src.business.Professor;
import src.business.Student;
import src.business.User;
import src.dao.interfaces.ProfessorDAO;
import src.dao.interfaces.StudentDAO;
import src.dao.interfaces.UserDAO;

import src.utils.InputValidator;

public class UserInterface {

        public static Scanner in = new Scanner(System.in).useDelimiter(System.lineSeparator());

        public static void UserList(List<User> users) {
                System.out.println("+------------------+");
                System.out.println("|       Users      |");
                System.out.println("+------------------+");
                System.out.println(
                                "+--------------------------------------------------------------------------------------+");
                System.out.println(
                                "| Id |        Name        |         Last Name        |       Registration Number       |");
                System.out.println(
                                "+--------------------------------------------------------------------------------------+");
                for (User user : users) {
                        System.out.printf("| %-3d| %-18s | %-24s | %-31s |\n", user.getId(), user.getName(),
                                        user.getLastName(),
                                        user.getRegistrationNumber());
                        System.out.println(
                                        "+--------------------------------------------------------------------------------------+");
                }
        }

        public static void StudentList(List<Student> students) {
                System.out.println("+------------------+");
                System.out.println("|     Students     |");
                System.out.println("+------------------+");
                System.out.println(
                                "+--------------------------------------------------------------------------------------------------------------------------+");
                System.out.println(
                                "| Id |        Name        |         Last Name        |       Registration Number       |         Major        |    Class   |");
                System.out.println(
                                "+--------------------------------------------------------------------------------------------------------------------------+");
                for (Student student : students) {
                        System.out.printf("| %-3d| %-18s | %-24s | %-31s | %-20s | %-10s |\n", student.getId(),
                                        student.getName(),
                                        student.getLastName(),
                                        student.getRegistrationNumber(),
                                        student.getMajor(),
                                        student.getGrade());
                        System.out.println(
                                        "+--------------------------------------------------------------------------------------------------------------------------+");
                }
        }

        public static void ProfessorList(List<Professor> professors) {
                System.out.println("+------------------+");
                System.out.println("|    Professors    |");
                System.out.println("+------------------+");
                System.out.println(
                                "+-------------------------------------------------------------------------------------------------------------+");
                System.out.println(
                                "| Id |        Name        |         Last Name        |      Department      |       Registration Number       |");
                System.out.println(
                                "+-------------------------------------------------------------------------------------------------------------+");
                for (Professor professor : professors) {
                        System.out.printf("| %-3d| %-18s | %-24s | %-20s | %-31s |\n", professor.getId(),
                                        professor.getName(),
                                        professor.getLastName(),
                                        professor.getDepartment(),
                                        professor.getRegistrationNumber());
                        System.out.println(
                                        "+-------------------------------------------------------------------------------------------------------------+");
                }
        }

        public static void ShowDetails(User user, UserDAO userDAO, StudentDAO studentDAO, ProfessorDAO professorDAO) {
                int input = -1;
                if (user == null)
                        return;
                user.showDetails();
                do {

                        System.out.println("\t\t+----------------------------------------+");
                        System.out.println("\t\t|                                        |");
                        System.out.println("\t\t|     1- Update User                     |");
                        System.out.println("\t\t|     2- Delete User                     |");
                        System.out.println("\t\t|     3- Back                            |");
                        System.out.println("\t\t|                                        |");
                        System.out.println("\t\t+----------------------------------------+");
                        System.out.print("Pick your choice : ");

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

                } while (input < 1 || input > 3);

                if (input == 1) {
                        String name = InputValidator.promptAndParseNullableString("Name : ");
                        String lastName = InputValidator.promptAndParseNullableString("Last Name : ");
                        String registrationNumber = InputValidator
                                        .promptAndParseNullableString("Registration Number : ");
                        if (user instanceof Student) {
                                Student student = (Student) user;
                                String major = InputValidator.promptAndParseNullableString("Major : ");
                                String grade = InputValidator.promptAndParseNullableString("Class : ");

                                studentDAO.update(student,
                                                new String[] { name, lastName, registrationNumber, grade, major });
                        } else {
                                Professor professor = (Professor) user;
                                String department = InputValidator.promptAndParseNullableString("Department : ");
                                professorDAO.update(professor,
                                                new String[] { name, lastName, registrationNumber, department });
                        }
                } else if (input == 2) {
                        System.out.println("Are you sure you want to delete this user ?\n 1 - Yes \t 2 - No");

                        int choice = InputValidator.promptAndParseInt("Pick your choice : ", 1, 2);

                        if (choice == 1) {
                                userDAO.delete(user);
                        }
                }
        }
}
