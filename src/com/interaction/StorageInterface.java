package com.interaction;

import com.elements.Worker;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface StorageInterface<T> {
    void clear();

    Date getInitializationDate();

    int size();

    void put(T worker);

    HashSet<Worker> getCollection();

    Worker generateId(Worker worker);

    List<Long> getIdList();
}
