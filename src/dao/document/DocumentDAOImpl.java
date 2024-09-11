package src.dao.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.Document;
import src.dao.interfaces.DAO;

public class DocumentDAOImpl implements DAO<Document> {
    private List<Document> documents = new ArrayList<>();

    @Override
    public Optional<Document> get(long id) {
        throw new UnsupportedOperationException("Get operation is not supported in DocumentDAO.");
    }

    @Override
    public List<Document> getAll() {
        return documents;
    }

    public void save(Document t) {
        throw new UnsupportedOperationException("Save operation is not supported in DocumentDAO.");
    }

    public void update(Document t, String[] params) {
        throw new UnsupportedOperationException("Update operation is not supported in DocumentDAO.");
    }

    public void delete(Document t) {
        throw new UnsupportedOperationException("Delete operation is not supported in DocumentDAO.");
    }
}
