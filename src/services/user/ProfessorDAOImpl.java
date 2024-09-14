package src.services.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.Professor;
import src.dao.interfaces.ProfessorDAO;
import src.db.DatabaseConnection;

public class ProfessorDAOImpl implements ProfessorDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.professor WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.professor";
    private static final String SQL_INSERT = "INSERT INTO public.professor(name, last_name, registration_number, department) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.professor SET name=?, last_name=?, registration_number=?, department=? WHERE id = ?;";
    private static final String SQL_DELETE = "DELETE FROM public.professor WHERE id = ?";

    @Override
    public Optional<Professor> get(long id) {
        Professor professor = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                professor = new Professor(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("registration_number"),
                        resultSet.getString("department"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(professor);
    }

    @Override
    public List<Professor> getAll() {
        List<Professor> professors = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String registrationNumber = resultSet.getString("registration_number");
                String department = resultSet.getString("department");

                Professor professor = new Professor(id, name, lastName, registrationNumber, department);
                professors.add(professor);

                connection.close();

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return professors;
    }

    public void save(Professor professor) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, professor.getName());
            statement.setString(2, professor.getLastName());
            statement.setString(3, professor.getRegistrationNumber());
            statement.setString(4, professor.getDepartment());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Professor professor, String[] params) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, params[0] != null && !params[0].isEmpty() ? params[0] : professor.getName());
            statement.setString(2, params[1] != null && !params[1].isEmpty() ? params[1] : professor.getLastName());
            statement.setString(3,
                    params[2] != null && !params[2].isEmpty() ? params[2] : professor.getRegistrationNumber());
            statement.setString(4, params[3] != null && !params[3].isEmpty() ? params[3] : professor.getDepartment());
            statement.setLong(5, professor.getId());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Professor professor) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, professor.getId());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
