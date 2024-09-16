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

import src.business.UniversityThesis;
import src.dao.interfaces.UniversityThesisDAO;
import src.db.DatabaseConnection;

public class UniversityThesisDAOImpl implements UniversityThesisDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.university_thesis WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.university_thesis WHERE is_deleted = false ORDER BY id ASC";
    private static final String SQL_INSERT = "INSERT INTO public.university_thesis(title, author, publication_date, page_numbers, field) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.university_thesis SET title=?, author=?, publication_date=?, page_numbers=?, field=? WHERE id = ?;";

    @Override
    public Optional<UniversityThesis> get(long id) {
        UniversityThesis universityThesis = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                universityThesis = new UniversityThesis(
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

        return Optional.ofNullable(universityThesis);
    }

    @Override
    public List<UniversityThesis> getAll() {
        List<UniversityThesis> thesis = new ArrayList<>();
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
                String field = resultSet.getString("field");

                UniversityThesis universityThesis = new UniversityThesis(id, title, author, publicationDate, pageNumbers,
                        field);

                thesis.add(universityThesis);

                connection.close();

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving thesis: " + e.getMessage());
            e.printStackTrace();
        }
        return thesis;
    }

    @Override
    public void save(UniversityThesis thesis) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, thesis.getTitle());
            statement.setString(2, thesis.getAuthor());
            statement.setDate(3, Date.valueOf(thesis.getPublicationDate()));
            statement.setInt(4, thesis.getPageNumbers());
            statement.setString(5, thesis.getField());

            statement.executeUpdate();
            connection.close();

            System.out.println("University thesis added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UniversityThesis thesis, String[] params) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, params[0] != null && !params[0].isEmpty() ? params[0] : thesis.getTitle());
            statement.setString(2, params[1] != null && !params[1].isEmpty() ? params[1] : thesis.getAuthor());
            statement.setDate(3, params[2] != null && !params[2].isEmpty()
                    ? Date.valueOf(LocalDate.parse(params[2]))
                    : Date.valueOf(thesis.getPublicationDate()));
            statement.setInt(4, params[3] != null && !params[3].isEmpty()
                    ? Integer.parseInt(params[3])
                    : thesis.getPageNumbers());
            statement.setString(5, params[4] != null && !params[4].isEmpty() ? params[4] : thesis.getField());
            statement.setLong(6, thesis.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("University thesis updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UniversityThesis thesis) {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
