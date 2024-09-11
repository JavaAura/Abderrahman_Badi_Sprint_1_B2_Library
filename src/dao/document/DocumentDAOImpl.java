package src.dao.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.Document;
import src.dao.interfaces.DocumentDAO;

public class DocumentDAOImpl implements DocumentDAO {
    private List<Document> documents = new ArrayList<>();

    @Override
    public Optional<Document> get(long id) {
        throw new UnsupportedOperationException("Get operation is not supported in DocumentDAOImpl.");
    }

    @Override
    public List<Document> getAll() {
        return documents;
    }

    public void save(Document t) {
        throw new UnsupportedOperationException("Save operation is not supported in DocumentDAOImpl.");
    }

    public void update(Document t, String[] params) {
        throw new UnsupportedOperationException("Update operation is not supported in DocumentDAOImpl.");
    }

    public void delete(Document t) {
        throw new UnsupportedOperationException("Delete operation is not supported in DocumentDAOImpl.");
    }
}
