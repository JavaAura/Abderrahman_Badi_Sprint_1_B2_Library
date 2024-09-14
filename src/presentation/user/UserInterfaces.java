package src.presentation.user;

import java.util.List;

import src.business.Professor;
import src.business.Student;
import src.business.User;

public class UserInterfaces {

    public static void UserList(List<User> users) {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("| Id |        Name        |         Last Name        |       Registration Number       |");
        System.out.println("+--------------------------------------------------------------------------------------+");
        for (User user : users) {
            System.out.printf("| %-3d| %-18s | %-24s | %-31s |\n", user.getId(), user.getName(),
                    user.getLastName(),
                    user.getRegistrationNumber());
            System.out.println(
                    "+--------------------------------------------------------------------------------------+");
        }
    }

    public static void StudentList(List<Student> students) {
        System.out.println(
                "+--------------------------------------------------------------------------------------------------------------------------+");
        System.out.println(
                "| Id |        Name        |         Last Name        |       Registration Number       |         Major        |    Class   |");
        System.out.println(
                "+--------------------------------------------------------------------------------------------------------------------------+");
        for (Student student : students) {
            System.out.printf("| %-3d| %-18s | %-24s | %-31s | %-20s | %-10s |\n", student.getId(), student.getName(),
                    student.getLastName(),
                    student.getRegistrationNumber(),
                    student.getMajor(),
                    student.getGrade());
            System.out.println(
                    "+--------------------------------------------------------------------------------------------------------------------------+");
        }
    }

    public static void ProfessorList(List<Professor> professors) {
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------+");
        System.out.println(
                "| Id |        Name        |         Last Name        |      Department      |       Registration Number       |");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------+");
        for (Professor professor : professors) {
            System.out.printf("| %-3d| %-18s | %-24s | %-20s | %-31s |\n", professor.getId(), professor.getName(),
                    professor.getLastName(),
                    professor.getDepartment(),
                    professor.getRegistrationNumber());
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
        }
    }

    public static void ShowDetails(User user) {
        if (user == null)
            return;
        user.showDetails();
    }
}
