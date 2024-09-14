package src.services.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.UniversityThesis;
import src.dao.interfaces.UniversityThesisDAO;

public class UniversityThesisDAOImpl implements UniversityThesisDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM FROM public.university_thesis WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM FROM public.university_thesis WHERE is_deleted = false";
    private static final String SQL_INSERT = "INSERT INTO FROM public.university_thesis(title, author, publication_date, page_numbers, field) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE FROM public.university_thesis SET title=?, author=?, publication_date=?, page_numbers=?, field=? WHERE id = ?;";

    @Override
    public Optional<UniversityThesis> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<UniversityThesis> getAll() {
        List<UniversityThesis> thesis = new ArrayList<>();
        return thesis;
    }

    @Override
    public void save(UniversityThesis thesis) {

    }

    @Override
    public void update(UniversityThesis thesis, String[] params) {

    }

    @Override
    public void delete(UniversityThesis thesis) {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
