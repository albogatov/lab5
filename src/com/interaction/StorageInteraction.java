package com.interaction;

import com.elements.Status;
import com.elements.Worker;

import java.util.*;

public class StorageInteraction implements InteractionInterface {

    private static Storage storage;

    public StorageInteraction(Storage storage) {
        StorageInteraction.storage = storage;
    }

    public String info() {
        System.out.println("Дата создания коллекции: " + storage.getInitializationDate() + "\n"
                + "Тип коллекции: " + storage.getCollection().getClass() + "\n"
                + "Размер коллекции: " + storage.getCollection().size());
        return null;
    }

    public String show() {
        for (Worker w : storage.getCollection()) {
            w.displayWorker();
        }
        return null;
    }

    public void add(Worker worker) {
        worker = storage.generateId(worker);
        storage.put(worker);
    }

    public void update(long id, Worker worker) {
        Iterator<Worker> itr = storage.getCollection().iterator();
        Worker oldWorker = null;
        while (itr.hasNext()) {
            Worker w = itr.next();
            if (w.getId() == id) {
                oldWorker = w;
                break;
            }
        }
        storage.getCollection().remove(oldWorker);
        worker.setId(id);
        storage.put(worker);
    }

    public void remove_by_id(long id) {
        Iterator<Worker> itr = storage.getCollection().iterator();
        Worker worker = null;
        while (itr.hasNext()) {
            Worker w = itr.next();
            if (w.getId() == id) {
                worker = w;
                break;
            }
        }
        storage.getCollection().remove(worker);
    }

    public void clear() {
        storage.clear();
    }

    public void save() {

    }

    public void exit() {
        System.exit(0);
    }

    public void add_if_min(Worker worker) {
        HashSet<Worker> workers = storage.getCollection();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        if (worker.getSalary() < toBeSortedWorkers.get(0).getSalary())
            storage.put(worker);
    }

    public void remove_greater(Worker worker) {
        HashSet<Worker> workers = storage.getCollection();
        Integer comparisonSalary = worker.getSalary();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        List<Worker> toBeRemovedWorkers = new ArrayList<>();
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        for (Worker w : toBeSortedWorkers) {
            if (w.getSalary() > comparisonSalary)
                toBeRemovedWorkers.add(w);
        }
        for (Worker w : toBeRemovedWorkers) {
            storage.getCollection().remove(w);
        }
    }

    public void remove_lower(Worker worker) {
        HashSet<Worker> workers = storage.getCollection();
        Integer comparisonSalary = worker.getSalary();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        List<Worker> toBeRemovedWorkers = new ArrayList<>();
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        for (Worker w : toBeSortedWorkers) {
            if (w.getSalary() < comparisonSalary)
                toBeRemovedWorkers.add(w);
        }
        for (Worker w : toBeRemovedWorkers) {
            storage.getCollection().remove(w);
        }
    }

    public int count_by_status(Status status) {
        Iterator<Worker> itr = storage.getCollection().iterator();
        int counter = 0;
        while (itr.hasNext()) {
            Worker w = itr.next();
            Status s = w.getStatus();
            if (s == status) {
                counter++;
            }
        }
        return counter;
    }

    public List<String> print_ascending() {
        HashSet<Worker> workers = storage.getCollection();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        for (Worker w : toBeSortedWorkers) {
            w.displayWorker();
        }
        return null;
    }

    public List<String> print_unique_organization() {
        List<String> organizations = new ArrayList<>();
        for (Worker w : storage.getCollection()) {
            if (!(w.getOrganization() == null)) {
                String orgName = w.getOrganizationName();
                if (!organizations.contains(orgName))
                    organizations.add(orgName);
            }
        }
        return organizations;
    }

    public int getSize() {
        return storage.getCollection().size();
    }

    public boolean findById(long id) {
        return storage.getIdList().contains(id);
    }
}
