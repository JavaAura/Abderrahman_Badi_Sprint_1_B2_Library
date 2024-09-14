package src.services.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.Magazine;
import src.dao.interfaces.MagazineDAO;

public class MagazineDAOImpl implements MagazineDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.magazine WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.magazine WHERE is_deleted = false";
    private static final String SQL_INSERT = "INSERT INTO public.magazine(title, author, publication_date, page_numbers, isbn) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE public.magazine SET title=?, author=?, publication_date=?, page_numbers=?, isbn=? WHERE id = ?;";

    @Override
    public Optional<Magazine> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Magazine> getAll() {
        List<Magazine> magazines = new ArrayList<>();
        return magazines;
    }

    @Override
    public void save(Magazine magazine) {

    }

    @Override
    public void update(Magazine magazine, String[] params) {

    }

    @Override
    public void delete(Magazine magazine) {
        throw new UnsupportedOperationException("Delete operation is not supported.");
    }
}
