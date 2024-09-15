package src.services.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.Student;
import src.dao.interfaces.StudentDAO;
import src.db.DatabaseConnection;

public class StudentDAOImpl implements StudentDAO {
    
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.student WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.student WHERE is_deleted = false ORDER BY id ASC";
    private static final String SQL_INSERT = "INSERT INTO public.student(name, last_name, registration_number, grade, major) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.student SET name=?, last_name=?, registration_number=?, grade=?, major=? WHERE id = ?;";

    @Override
    public Optional<Student> get(long id) {
        Student student = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("registration_number"),
                        resultSet.getString("class"),
                        resultSet.getString("major"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String registrationNumber = resultSet.getString("registration_number");
                String grade = resultSet.getString("grade");
                String major = resultSet.getString("major");

                Student student = new Student(id, name, lastName, registrationNumber, grade, major);
                students.add(student);

                connection.close();

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return students;
    }

    public void save(Student student) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, student.getName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getRegistrationNumber());
            statement.setString(4, student.getGrade());
            statement.setString(5, student.getMajor());

            statement.executeUpdate();
            connection.close();

            System.out.println("Student created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student, String[] params) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, params[0] != null && !params[0].isEmpty() ? params[0] : student.getName());
            statement.setString(2, params[1] != null && !params[1].isEmpty() ? params[1] : student.getLastName());
            statement.setString(3,
                    params[2] != null && !params[2].isEmpty() ? params[2] : student.getRegistrationNumber());
            statement.setString(4, params[3] != null && !params[3].isEmpty() ? params[3] : student.getGrade());
            statement.setString(5, params[4] != null && !params[4].isEmpty() ? params[4] : student.getMajor());
            statement.setLong(6, student.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("Student updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Student student) {
        throw new UnsupportedOperationException("Get operation is not supported.");
    }
}
