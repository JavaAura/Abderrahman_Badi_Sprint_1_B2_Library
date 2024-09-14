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

    private static final String SQL_LIST = "SELECT * FROM public.\"user\" ORDER BY id ASC";

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
        }

        return users;
    }

    /* ----------------- UNSEUPPORTED METHODS ----------------- */

    @Override
    public Optional<User> get(long id) {
        throw new UnsupportedOperationException("Get operation is not supported in UserDAOImpl.");
    }

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Save operation is not supported in UserDAOImpl.");
    }

    @Override
    public void update(User user, String[] params) {
        throw new UnsupportedOperationException("Update operation is not supported in UserDAOImpl.");
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Delete operation is not supported in UserDAOImpl.");
    }
}
