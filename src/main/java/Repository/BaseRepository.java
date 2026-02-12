package Repository;

import Domain.Identifiable.Identifiable;
import Utils.Logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class BaseRepository<T extends Identifiable> {
    protected final Map<String, T> storage = new HashMap<>();
    protected final Logger logger;

    protected BaseRepository(Logger logger) {
        this.logger = logger;
    }

    public Optional<T> findByKey(String key) {
        return Optional.ofNullable(storage.get(key));
    }

    public boolean exists(String key) {
        return storage.containsKey(key);
    }

    public abstract boolean exists(T entity);

    public void add(T entity) {
        safetyAdd(entity);
    }

    protected abstract void safetyAdd(T entity);

    public void delete(T entity) {
        safetyDelete(entity);
    }

    protected abstract void safetyDelete(T entity);

    public void update(T entity) {
        safetyUpdate(entity);
    }

    protected abstract void safetyUpdate(T entity);

}