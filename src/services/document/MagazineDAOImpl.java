package src.services.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import src.business.Magazine;
import src.dao.interfaces.MagazineDAO;
import src.db.DatabaseConnection;

public class MagazineDAOImpl implements MagazineDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.magazine WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.magazine WHERE is_deleted = false";
    private static final String SQL_INSERT = "INSERT INTO public.magazine(title, author, publication_date, page_numbers, isbn) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.magazine SET title=?, author=?, publication_date=?, page_numbers=?, isbn=? WHERE id = ?;";

    @Override
    public Optional<Magazine> get(long id) {
        Magazine magazine = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                magazine = new Magazine(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDate("publication_date").toLocalDate(),
                        resultSet.getInt("page_numbers"),
                        resultSet.getInt("isbn"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(magazine);
    }

    @Override
    public List<Magazine> getAll() {
        List<Magazine> magazines = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                LocalDate publicationDate = resultSet.getDate("publication_date").toLocalDate();
                int pageNumbers = resultSet.getInt("page_numbers");
                int isbn = resultSet.getInt("isbn");

                Magazine magazine = new Magazine(id, title, author, publicationDate, pageNumbers, isbn);

                magazines.add(magazine);

                connection.close();

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return magazines;
    }

    @Override
    public void save(Magazine magazine) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, magazine.getTitle());
            statement.setString(2, magazine.getAuthor());
            statement.setDate(3, Date.valueOf(magazine.getPublicationDate()));
            statement.setInt(4, magazine.getPageNumbers());
            statement.setInt(5, magazine.getIsbn());

            statement.executeUpdate();
            connection.close();

            System.out.println("Magazine added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Magazine magazine, String[] params) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, params[0] != null && !params[0].isEmpty() ? params[0] : magazine.getTitle());
            statement.setString(2, params[1] != null && !params[1].isEmpty() ? params[1] : magazine.getAuthor());
            statement.setDate(3, params[2] != null && !params[2].isEmpty()
                    ? Date.valueOf(LocalDate.parse(params[2]))
                    : Date.valueOf(magazine.getPublicationDate()));
            statement.setInt(4, params[3] != null && !params[3].isEmpty()
                    ? Integer.parseInt(params[3])
                    : magazine.getPageNumbers());
            statement.setInt(5, params[4] != null && !params[4].isEmpty() ? Integer.parseInt(params[4]) : magazine.getIsbn());
            statement.setLong(6, magazine.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("University thesis updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Magazine magazine) {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
