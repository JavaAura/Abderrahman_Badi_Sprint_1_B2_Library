package src.services.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.User;
import src.business.Student;
import src.dao.interfaces.UserDAO;
import src.db.DatabaseConnection;

public class UserDAOImpl implements UserDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.user WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.\"user\" WHERE is_deleted = false ORDER BY id ASC";
    private static final String SQL_DELETE = "UPDATE public.user SET is_deleted = ? WHERE id = ?;";

    @Override
    public Optional<User> get(long id) {
        User user = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("registration_number"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>(); 

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String registrationNumber = resultSet.getString("registration_number");

                Student user = new Student(id, name, lastName, registrationNumber);
                users.add(user);

                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void delete(User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);

            statement.setBoolean(1, true);

            statement.setLong(2, user.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("User deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ----------------- UNSEUPPORTED METHODS ----------------- */

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Save operation is not supported in UserDAOImpl.");
    }

    @Override
    public void update(User user, String[] params) {
        throw new UnsupportedOperationException("Update operation is not supported in UserDAOImpl.");
    }
}
