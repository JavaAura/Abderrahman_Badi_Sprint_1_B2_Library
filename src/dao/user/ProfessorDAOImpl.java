package src.dao.user;

import java.util.HashMap;
import java.util.Optional;

import src.business.Professor;
import src.dao.interfaces.ProfessorDAO;

public class ProfessorDAOImpl implements ProfessorDAO {
    private HashMap<Long, Professor> professors = new HashMap<>();

    @Override
    public Optional<Professor> get(long id) {
        return Optional.empty();
    }

    @Override
    public HashMap<Long, Professor> getAll() {

        return professors;
    }

    @Override
    public void save(Professor professor) {

    }

    @Override
    public void update(Professor professor, String[] params) {

    }

    @Override
    public void delete(Professor professor) {

    }
}
