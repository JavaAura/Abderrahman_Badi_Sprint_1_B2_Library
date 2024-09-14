package src.services.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.ScientificJournal;
import src.dao.interfaces.ScientificJournalDAO;

public class ScientificJournalDAOImpl implements ScientificJournalDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.scientific_journal WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.scientific_journal WHERE is_deleted = false";
    private static final String SQL_INSERT = "INSERT INTO public.scientific_journal(title, author, publication_date, page_numbers, field) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.scientific_journal SET title=?, author=?, publication_date=?, page_numbers=?, field=? WHERE id = ?;";

    @Override
    public Optional<ScientificJournal> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ScientificJournal> getAll() {
        List<ScientificJournal> journals = new ArrayList<>();
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
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
