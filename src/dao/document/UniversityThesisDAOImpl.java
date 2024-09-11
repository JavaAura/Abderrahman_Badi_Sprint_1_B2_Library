package src.dao.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.UniversityThesis;
import src.dao.interfaces.DAO;

public class UniversityThesisDAOImpl implements DAO<UniversityThesis> {

    private List<UniversityThesis> thesis = new ArrayList<>();

    @Override
    public Optional<UniversityThesis> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<UniversityThesis> getAll() {

        return thesis;
    }

    @Override
    public void save(UniversityThesis t) {

    }

    @Override
    public void update(UniversityThesis t, String[] params) {

    }

    @Override
    public void delete(UniversityThesis t) {

    }
    
}
