package src.dao.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.Book;
import src.dao.interfaces.DAO;

public class BookDAOImpl implements DAO<Book> {
    private List<Book> books = new ArrayList<>();

    @Override
    public Optional<Book> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {

        return books;
    }

    @Override
    public void save(Book t) {

    }

    @Override
    public void update(Book t, String[] params) {

    }

    @Override
    public void delete(Book t) {

    }
}
