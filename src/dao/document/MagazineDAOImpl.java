package src.dao.document;

import java.util.HashMap;
import java.util.Optional;

import src.business.Magazine;
import src.dao.interfaces.MagazineDAO;

public class MagazineDAOImpl implements MagazineDAO {
    private HashMap<Long, Magazine> magazines = new HashMap<>();

    @Override
    public Optional<Magazine> get(long id) {
        return Optional.empty();
    }

    @Override
    public HashMap<Long, Magazine> getAll() {

        return magazines;
    }

    @Override
    public void save(Magazine magazine) {

    }

    @Override
    public void update(Magazine magazine, String[] params) {

    }

    @Override
    public void delete(Magazine magazine) {

    }
}
