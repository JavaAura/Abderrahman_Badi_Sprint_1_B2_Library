package src.dao.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import src.business.ScientificJournal;
import src.dao.interfaces.ScientificJournalDAO;

public class ScientificJournalDAOImpl implements ScientificJournalDAO {
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
    public void save(ScientificJournal journal) {

    }

    @Override
    public void update(ScientificJournal journal, String[] params) {

    }

    @Override
    public void delete(ScientificJournal journal) {

    }
}
