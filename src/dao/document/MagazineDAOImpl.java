package src.dao.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import src.business.Magazine;
import src.dao.interfaces.MagazineDAO;

public class MagazineDAOImpl implements MagazineDAO {
    private List<Magazine> magazines = new ArrayList<>();

    @Override
    public Optional<Magazine> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Magazine> getAll() {

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
