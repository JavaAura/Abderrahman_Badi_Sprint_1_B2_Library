package src.dao.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.User;
import src.dao.interfaces.UserDAO;

public class UserDAOImpl implements UserDAO {
    private List<User> users = new ArrayList<>();

    @Override
    public Optional<User> get(long id) {
        throw new UnsupportedOperationException("Get operation is not supported in UserDAOImpl.");
    }

    @Override
    public List<User> getAll() {

        return users;
    }

    @Override
    public void save(User t) {
        throw new UnsupportedOperationException("Save operation is not supported in UserDAOImpl.");
    }

    @Override
    public void update(User t, String[] params) {
        throw new UnsupportedOperationException("Update operation is not supported in UserDAOImpl.");
    }

    @Override
    public void delete(User t) {
        throw new UnsupportedOperationException("Delete operation is not supported in UserDAOImpl.");
    }
}
