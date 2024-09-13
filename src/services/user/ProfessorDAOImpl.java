package src.services.user;

import java.util.List;
import java.util.ArrayList;
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
    public void save(Professor professor) {

    }

    @Override
    public void update(Professor professor, String[] params) {

    }

    @Override
    public void delete(Professor professor) {

    }
}
