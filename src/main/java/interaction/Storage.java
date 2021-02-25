package interaction;

import elements.Worker;

import java.util.*;

/**
 * Класс-хранилище
 */
public class Storage implements StorageInterface<Worker> {

    private final HashSet<Worker> workers = new HashSet<>();
    private final Date creationDate;
    private final List<Long> idList = new ArrayList<>();

    /**
     * Стандартный конструктор, задает дату создания
     */
    public Storage() {
        creationDate = new Date();
    }

    /**
     * Метод очистки хранилища
     */
    public void clear() {
        workers.clear();
    }

    /**
     * Метод, возвращающий дату создания коллекции
     *
     * @return - дата создания
     */
    public Date getInitializationDate() {
        return creationDate;
    }

    /**
     * Метод, возвращающий число элементов коллекции
     *
     * @return - размер
     */
    public int size() {
        return workers.size();
    }

    /**
     * Метод, помещающий объект в коллекцию
     *
     * @param worker - помещаемый объект
     */
    public void put(Worker worker) {
        workers.add(worker);
    }

    /**
     * Метод, возвращающий коллекцию
     *
     * @return - коллекция
     */
    public HashSet<Worker> getCollection() {
        return workers;
    }

    /**
     * Метод генерации идентификатора
     *
     * @param worker - объект, для которого генерируется ID
     * @return - объект с установленным ID
     */
    public Worker generateId(Worker worker) {
        long id;
        int bound = 1 + workers.size() * 10;
        Random multiplier = new Random();
        do {
            id = worker.getCreationDate().toInstant().toEpochMilli() * multiplier.nextInt(bound);
        }
        while (idList.contains(id));
        worker.setId(id);
        idList.add(id);
        return worker;
    }

    /**
     * Метод, возвращающий список ID
     *
     * @return - список ID
     */
    public List<Long> getIdList() {
        return idList;
    }
}
