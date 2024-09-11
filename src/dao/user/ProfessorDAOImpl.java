package src.dao.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.Professor;
import src.dao.interfaces.ProfessorDAO;

public class ProfessorDAOImpl implements ProfessorDAO {
    private List<Professor> professors = new ArrayList<>();


    @Override
    public Optional<Professor> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Professor> getAll() {

        return professors;
    }

    @Override
    public void save(Professor t) {

    }

    @Override
    public void update(Professor t, String[] params) {

    }

    @Override
    public void delete(Professor t) {

    }
}
