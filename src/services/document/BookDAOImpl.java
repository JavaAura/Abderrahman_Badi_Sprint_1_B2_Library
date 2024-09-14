package src.services.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.Book;
import src.dao.interfaces.BookDAO;

public class BookDAOImpl implements BookDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.book WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.book WHERE is_deleted = false";
    private static final String SQL_INSERT = "INSERT INTO public.book(title, author, publication_date, page_numbers, \"number\") VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.book SET title=?, author=?, publication_date=?, page_numbers=?, \"number\"=? WHERE id = ?;";

    @Override
    public Optional<Book> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
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
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
