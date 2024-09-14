package src.services.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.Document;
import src.dao.interfaces.DocumentDAO;

public class DocumentDAOImpl implements DocumentDAO {
    private List<Document> documentsList = new ArrayList<>();

    @Override
    public List<Document> getAll() {
        return documentsList;
    }

    /* ----------------- UNSEUPPORTED METHODS ----------------- */

    @Override
    public Optional<Document> get(long id) {
        throw new UnsupportedOperationException("Get operation is not supported in DocumentDAOImpl.");
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
