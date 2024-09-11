package src.dao.user;

import java.util.HashMap;
import java.util.Optional;

import src.business.Student;
import src.dao.interfaces.StudentDAO;

public class StudentDAOImpl implements StudentDAO {
    private HashMap<Long, Student> students = new HashMap<>();

    @Override
    public Optional<Student> get(long id) {
        return Optional.empty();
    }

    @Override
    public HashMap<Long, Student> getAll() {

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
