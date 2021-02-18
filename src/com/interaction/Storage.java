package com.interaction;

import com.elements.Worker;

import java.util.*;

public class Storage implements StorageInterface<Worker> {

    private final HashSet<Worker> workers = new HashSet<>();
    private final Date creationDate;
    private final List<Long> idList = new ArrayList<>();


    public Storage() {
        creationDate = new Date();
    }

    public void clear() {
        workers.clear();
    }

    public Date getInitializationDate() {
        return creationDate;
    }

    public int size() {
        return workers.size();
    }

    public void put(Worker worker) {
        workers.add(worker);
    }

    public HashSet<Worker> getCollection() {
        return workers;
    }

    public Worker generateId(Worker worker) {
        long id;
        int bound = workers.size() * 10;
        Random multiplier = new Random();
        do {
            id = worker.getCreationDate().toInstant().toEpochMilli() * multiplier.nextInt(bound);
        }
        while (idList.contains(id));
        worker.setId(id);
        idList.add(id);
        return worker;
    }

    public List<Long> getIdList() {
        return idList;
    }
}
