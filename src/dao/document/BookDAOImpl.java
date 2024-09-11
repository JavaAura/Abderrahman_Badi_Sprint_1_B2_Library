package src.dao.document;


import java.util.HashMap;
import java.util.Optional;

import src.business.Book;
import src.dao.interfaces.BookDAO;

public class BookDAOImpl implements BookDAO {
    private HashMap<Long, Book> books = new HashMap<>();

    @Override
    public Optional<Book> get(long id) {
        return Optional.empty();
    }

    @Override
    public HashMap<Long, Book> getAll() {

        return books;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book book, String[] params) {

    }

    @Override
    public void delete(Book book) {

    }
}
