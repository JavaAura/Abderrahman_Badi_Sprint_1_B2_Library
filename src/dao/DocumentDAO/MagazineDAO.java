package src.dao.DocumentDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.Magazine;
import src.dao.DAO;

public class MagazineDAO implements DAO<Magazine> {
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
    public void save(Magazine t) {

    }

    @Override
    public void update(Magazine t, String[] params) {

    }

    @Override
    public void delete(Magazine t) {

    }
}
