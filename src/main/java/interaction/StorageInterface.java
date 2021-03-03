package interaction;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Интерфейс для классов для хранения коллекции
 *
 * @param <T> тип объектов коллекции
 */
public interface StorageInterface<T> {
    void clear();

    Date getInitializationDate();

    int size();

    void put(T worker);

    HashSet<T> getCollection();

    T generateId(T worker) throws Exception;

    List<Long> getIdList();
}
