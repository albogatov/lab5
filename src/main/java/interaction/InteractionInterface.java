package interaction;

import elements.Status;
import elements.Worker;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для классов, управляющих взаимодействием с коллекцией.
 */
public interface InteractionInterface {

    String info();

    String show();

    void add(Worker worker) throws Exception;

    void update(long id, Worker worker);

    void removeById(long id);

    void clear();

    void save() throws IOException;

    void exit();

    void addIfMin(Worker worker);

    void removeGreater(Worker worker);

    void removeLower(Worker worker);

    int countByStatus(Status status);

    List<String> printAscending();

    List<String> printUniqueOrganization();

    int getSize();

    boolean findById(long key);

    boolean checkChanges();

    String returnSeparator();
}
