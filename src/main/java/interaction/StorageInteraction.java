package interaction;

import com.opencsv.CSVWriter;
import elements.Status;
import elements.Worker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс-реализация взаимодействия с коллекцией
 */
public class StorageInteraction implements InteractionInterface {

    private static Storage storage;

    /**
     * Стандартный конструктор, задает хранилище, с которым будет работа
     * @param storage - хранилище
     */
    public StorageInteraction(Storage storage) {
        StorageInteraction.storage = storage;
    }

    /**
     * Метод, реализующий команду info
     * @return - информация о коллекции
     */
    public String info() {
        System.out.println("Дата создания коллекции: " + storage.getInitializationDate() + "\n"
                + "Тип коллекции: " + storage.getCollection().getClass() + "\n"
                + "Размер коллекции: " + storage.getCollection().size());
        return null;
    }

    /**
     * Метод, реализующий команду show
     * @return - строковое представление объектов коллекции
     */
    public String show() {
        for (Worker w : storage.getCollection()) {
            w.displayWorker();
        }
        return null;
    }

    /**
     * Метод, реализующий команду add
     * @param worker - добавляемый объект
     */
    public void add(Worker worker) {
        worker = storage.generateId(worker);
        storage.put(worker);
    }

    /**
     * Метод, реализующий команду update
     * @param id - ID обновляемого объекта
     * @param worker - новый объект коллекции
     */
    public void update(long id, Worker worker) {
        removeById(id);
        worker.setId(id);
        storage.put(worker);
    }

    /**
     * Метод, реализующий команду removeById
     * @param id - ID удаляемого объекта
     */
    public void removeById(long id) {
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

    /**
     * Метод, реализующий команду clear
     */
    public void clear() {
        storage.clear();
    }

    /**
     * Метод, реализующий команду save
     * @param filePath - путь итогового файла
     * @throws IOException - в случае некорректного ввода
     */
    public void save(String filePath) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(filePath));
        CSVWriter csvWriter = new CSVWriter(printWriter);
        String[] keyLine = { "name", "coordinates", "salary", "endDate", "position", "status", "organization", "orgType", "annualTurnover", "postalAddress" };
        csvWriter.writeNext(keyLine);
        HashSet<Worker> collection = storage.getCollection();
        for(Worker w : collection) {
            csvWriter.writeNext(new String[] {
                    w.getName(), w.getCoordinates().toString(), String.valueOf(w.getSalary()), String.valueOf(w.getEndDate()),
                    String.valueOf(w.getPositionString()), String.valueOf(w.getStatusString()), String.valueOf(w.getOrganizationName()),
                    String.valueOf(w.getOrganizationType()), String.valueOf(w.getAnnualTurnover()), String.valueOf(w.getPostalAddress())
            });
        }
        csvWriter.close();
    }

    /**
     * Метод, реализующий команду exit
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Метод, реализующий команду addIfMin
     * @param worker - добавляемый объект
     */
    public void addIfMin(Worker worker) {
        HashSet<Worker> workers = storage.getCollection();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        if (worker.getSalary() < toBeSortedWorkers.get(0).getSalary())
            storage.put(worker);
    }

    /**
     * Метод, реализующий команду removeGreater
     * @param worker - объект для сравнения
     */
    public void removeGreater(Worker worker) {
        HashSet<Worker> workers = storage.getCollection();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        List<Worker> toBeRemovedWorkers = new ArrayList<>();
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        for (Worker w : toBeSortedWorkers) {
            if (w.compareTo(worker) > 0)
                toBeRemovedWorkers.add(w);
        }
        for (Worker w : toBeRemovedWorkers) {
            storage.getCollection().remove(w);
        }
    }

    /**
     * Метод, реализующий команду removeLower
     * @param worker - объект для сравнения
     */
    public void removeLower(Worker worker) {
        HashSet<Worker> workers = storage.getCollection();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        List<Worker> toBeRemovedWorkers = new ArrayList<>();
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        for (Worker w : toBeSortedWorkers) {
            if (w.compareTo(worker) < 0)
                toBeRemovedWorkers.add(w);
        }
        for (Worker w : toBeRemovedWorkers) {
            storage.getCollection().remove(w);
        }
    }

    /**
     * Метод, реализующий команду countByStatus
     * @param status - статус
     * @return - число объектов с указанным статусом
     */
    public int countByStatus(Status status) {
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

    /**
     * Метод, реализующий команду printAscending
     * @return - отсортированное строковое представление коллекции
     */
    public List<String> printAscending() {
        HashSet<Worker> workers = storage.getCollection();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        for (Worker w : toBeSortedWorkers) {
            w.displayWorker();
        }
        return null;
    }

    /**
     * Метод, реализующий команду printUniqueOrganization
     * @return - список всех уникальных организаций
     */
    public List<String> printUniqueOrganization() {
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

    /**
     * Метод, возвращающий размер коллекции
     * @return - размер коллекции
     */
    public int getSize() {
        return storage.getCollection().size();
    }

    /**
     * Метод, проверяющий наличие объекта по ID
     * @param id - ID для поиска
     * @return - true если объект существует, иначе false
     */
    public boolean findById(long id) {
        return storage.getIdList().contains(id);
    }
}
