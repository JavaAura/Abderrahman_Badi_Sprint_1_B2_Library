package src.dao.user;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import src.business.Student;
import src.dao.interfaces.StudentDAO;

public class StudentDAOImpl implements StudentDAO {
    private List<Student> students = new ArrayList<>();

    @Override
    public Optional<Student> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> getAll() {

        return students;
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public void update(Student student, String[] params) {

    }

    @Override
    public void delete(Student student) {

    }
}
