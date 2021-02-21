package interaction;

import elements.Worker;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Интерфейс для классов для хранения коллекции
 * @param <T> - тип объектов коллекции
 */
public interface StorageInterface<T> {
    void clear();

    Date getInitializationDate();

    int size();

    void put(T worker);

    HashSet<Worker> getCollection();

    Worker generateId(Worker worker);

    List<Long> getIdList();
}
