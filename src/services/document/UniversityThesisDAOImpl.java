package src.services.document;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import src.business.UniversityThesis;
import src.dao.interfaces.UniversityThesisDAO;

public class UniversityThesisDAOImpl implements UniversityThesisDAO {

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
    public void save(UniversityThesis thesis) {

    }

    @Override
    public void update(UniversityThesis thesis, String[] params) {

    }

    @Override
    public void delete(UniversityThesis thesis) {

    }

}
