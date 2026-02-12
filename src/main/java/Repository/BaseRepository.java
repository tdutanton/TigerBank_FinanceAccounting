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

    public void add(T entity) {
        String id = entity.getId();
        if (storage.containsKey(id)) {
//            logger.info("Сущность %s уже существует в хранилище.%n", id);
            return;
        }
        storage.put(id, entity);
//        logger.info("Сохранена сущность: %s%n", id);
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public boolean exists(String id) {
        return storage.containsKey(id);
    }

    public boolean exists(T entity) {
        return exists(entity.getId());
    }

    public Iterable<T> findAll() {
        return storage.values();
    }

    public void remove(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
//            logger.info("Сущность %s удалена.%n", id);
        }
    }
}