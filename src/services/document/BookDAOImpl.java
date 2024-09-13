package src.services.document;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.Book;
import src.dao.interfaces.BookDAO;

public class BookDAOImpl implements BookDAO {
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
    public void save(Book book) {

    }

    @Override
    public void update(Book book, String[] params) {

    }

    @Override
    public void delete(Book book) {

    }
}
