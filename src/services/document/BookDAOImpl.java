package src.services.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import src.business.Book;
import src.dao.interfaces.BookDAO;
import src.db.DatabaseConnection;

public class BookDAOImpl implements BookDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.book WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.book WHERE is_deleted = false";
    private static final String SQL_INSERT = "INSERT INTO public.book(title, author, publication_date, page_numbers, \"number\") VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.book SET title=?, author=?, publication_date=?, page_numbers=?, \"number\"=? WHERE id = ?;";

    @Override
    public Optional<Book> get(long id) {
        Book book = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDate("publication_date").toLocalDate(),
                        resultSet.getInt("page_numbers"),
                        resultSet.getInt("number"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                LocalDate publicationDate = resultSet.getDate("publication_date").toLocalDate();
                int pageNumbers = resultSet.getInt("page_numbers");
                int isbn = resultSet.getInt("isbn");

                Book book = new Book(id, name, author, publicationDate, pageNumbers, isbn);

                books.add(book);

                connection.close();

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void save(Book book) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setDate(3, Date.valueOf(book.getPublicationDate()));
            statement.setInt(4, book.getPageNumbers());
            statement.setInt(5, book.getNumber());

            statement.executeUpdate();
            connection.close();

            System.out.println("Book added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book, String[] params) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, params[0] != null && !params[0].isEmpty() ? params[0] : book.getTitle());
            statement.setString(2, params[1] != null && !params[1].isEmpty() ? params[1] : book.getAuthor());
            statement.setDate(3, params[2] != null && !params[2].isEmpty()
                    ? Date.valueOf(LocalDate.parse(params[2]))
                    : Date.valueOf(book.getPublicationDate()));
            statement.setInt(4, params[3] != null && !params[3].isEmpty()
                    ? Integer.parseInt(params[3])
                    : book.getPageNumbers());
            statement.setInt(5,
                    params[4] != null && !params[4].isEmpty() ? Integer.parseInt(params[4]) : book.getNumber());
            statement.setLong(6, book.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("University thesis updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
