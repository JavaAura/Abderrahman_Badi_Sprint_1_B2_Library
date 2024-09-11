package src.dao.document;

import java.util.HashMap;
import java.util.Optional;

import src.business.Document;
import src.dao.interfaces.DocumentDAO;

public class DocumentDAOImpl implements DocumentDAO {
    private HashMap<Long, Document> documents = new HashMap<>();

    @Override
    public Optional<Document> get(long id) {
        throw new UnsupportedOperationException("Get operation is not supported in DocumentDAOImpl.");
    }

    @Override
    public HashMap<Long, Document> getAll() {
        return documents;
    }

    public void save(Document document) {
        throw new UnsupportedOperationException("Save operation is not supported in DocumentDAOImpl.");
    }

    public void update(Document document, String[] params) {
        throw new UnsupportedOperationException("Update operation is not supported in DocumentDAOImpl.");
    }

    public void delete(Document document) {
        throw new UnsupportedOperationException("Delete operation is not supported in DocumentDAOImpl.");
    }
}
