package src.dao.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.ScientificJournal;
import src.dao.interfaces.DAO;

public class ScientificJournalDAOImpl implements DAO<ScientificJournal> {
    private List<ScientificJournal> journals = new ArrayList<>();

    @Override
    public Optional<ScientificJournal> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ScientificJournal> getAll() {

        return journals;
    }

    @Override
    public void save(ScientificJournal t) {

    }

    @Override
    public void update(ScientificJournal t, String[] params) {

    }

    @Override
    public void delete(ScientificJournal t) {

    }
}
