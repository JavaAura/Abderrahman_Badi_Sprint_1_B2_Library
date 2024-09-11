package src.dao.document;

import java.util.HashMap;
import java.util.Optional;

import src.business.UniversityThesis;
import src.dao.interfaces.UniversityThesisDAO;

public class UniversityThesisDAOImpl implements UniversityThesisDAO {

    private HashMap<Long, UniversityThesis> thesis = new HashMap<>();

    @Override
    public Optional<UniversityThesis> get(long id) {
        return Optional.empty();
    }

    @Override
    public HashMap<Long, UniversityThesis> getAll() {

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

    }

}
