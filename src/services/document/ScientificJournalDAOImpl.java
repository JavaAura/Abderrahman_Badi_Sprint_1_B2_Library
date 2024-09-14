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

import src.business.ScientificJournal;
import src.dao.interfaces.ScientificJournalDAO;
import src.db.DatabaseConnection;

public class ScientificJournalDAOImpl implements ScientificJournalDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.scientific_journal WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.scientific_journal WHERE is_deleted = false";
    private static final String SQL_INSERT = "INSERT INTO public.scientific_journal(title, author, publication_date, page_numbers, field) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.scientific_journal SET title=?, author=?, publication_date=?, page_numbers=?, field=? WHERE id = ?;";

    @Override
    public Optional<ScientificJournal> get(long id) {
        ScientificJournal scientificJournal = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                scientificJournal = new ScientificJournal(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDate("publication_date").toLocalDate(),
                        resultSet.getInt("page_numbers"),
                        resultSet.getString("field"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(scientificJournal);
    }

    @Override
    public List<ScientificJournal> getAll() {
        List<ScientificJournal> journals = new ArrayList<>();
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
                String field = resultSet.getString("field");

                ScientificJournal scientificJournal = new ScientificJournal(id, name, author, publicationDate,
                        pageNumbers,
                        field);

                journals.add(scientificJournal);

                connection.close();

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return journals;
    }

    @Override
    public void save(ScientificJournal journal) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, journal.getTitle());
            statement.setString(2, journal.getAuthor());
            statement.setDate(3, Date.valueOf(journal.getPublicationDate()));
            statement.setInt(4, journal.getPageNumbers());
            statement.setString(5, journal.getField());

            statement.executeUpdate();
            connection.close();

            System.out.println("Scientific journal added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ScientificJournal journal, String[] params) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, params[0] != null && !params[0].isEmpty() ? params[0] : journal.getTitle());
            statement.setString(2, params[1] != null && !params[1].isEmpty() ? params[1] : journal.getAuthor());
            statement.setDate(3, params[2] != null && !params[2].isEmpty()
                    ? Date.valueOf(LocalDate.parse(params[2]))
                    : Date.valueOf(journal.getPublicationDate()));
            statement.setInt(4, params[3] != null && !params[3].isEmpty()
                    ? Integer.parseInt(params[3])
                    : journal.getPageNumbers());
            statement.setString(5, params[4] != null && !params[4].isEmpty() ? params[4] : journal.getField());
            statement.setLong(6, journal.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("Scientific journal updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ScientificJournal journal) {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
