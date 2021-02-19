package interaction;

import elements.Status;
import elements.Worker;

import java.util.List;

public interface InteractionInterface {

    String info();

    String show();

    void add(Worker worker);

    void update(long id, Worker worker);

    void remove_by_id(long id);

    void clear();

    void save();


    void exit();

    void add_if_min(Worker worker);

    void remove_greater(Worker worker);

    void remove_lower(Worker worker);

    int count_by_status(Status status);

    List<String> print_ascending();

    List<String> print_unique_organization();

    int getSize();

    boolean findById(long key);

}
