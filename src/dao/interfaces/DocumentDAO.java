package src.dao.interfaces;

import java.util.List;

import src.business.Document;

public interface DocumentDAO extends DAO<Document> {
    List<Document> findDocument(String s);
}
