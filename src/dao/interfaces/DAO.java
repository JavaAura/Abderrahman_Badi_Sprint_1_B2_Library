package src.dao.interfaces;

import java.util.HashMap;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(long id);

    HashMap<Long, T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
