package src.dao.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import src.business.User;
import src.dao.interfaces.UserDAO;

public class UserDAOImpl implements UserDAO {
    private List<User> users = new ArrayList<>();

    @Override
    public List<User> getAll() {

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
