package interaction;

import elements.Status;
import elements.Worker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Класс-реализация взаимодействия с коллекцией
 */
public final class StorageInteraction implements InteractionInterface {
    /**
     * Статическое поле-хранилище коллекции
     */
    private static Storage storage;
    /**
     * Статическое поле, содержащее путь к файлу с хранимой коллекцией
     */
    private static String originPath;
    /**
     * Статическое поле, содержит разделитель значений в оригинальном файле с коллекцией
     */
    private static char separator;
    /**
     * Статическое поле, отображающее наличие внесенных несохраненных изменений в хранимую коллекцию
     */
    private static boolean changesMade = false;

    /**
     * Стандартный конструктор, задает хранилище, с которым будет работа
     *
     * @param storage    хранилище
     * @param originPath путь к данным
     */
    public StorageInteraction(Storage storage, String originPath, char separator) {
        StorageInteraction.storage = storage;
        StorageInteraction.originPath = originPath;
        StorageInteraction.separator = separator;
    }

    /**
     * Метод, реализующий команду info
     *
     * @return информация о коллекции
     */
    public String info() {
        System.out.println("Дата создания коллекции: " + storage.getInitializationDate() + "\n"
                + "Тип коллекции: " + storage.getCollection().getClass() + "\n"
                + "Размер коллекции: " + storage.getCollection().size());
        return null;
    }

    /**
     * Метод, реализующий команду show
     *
     * @return Строковое представление объектов коллекции
     */
    public String show() {
        for (Worker w : storage.getCollection()) {
            w.displayWorker();
        }
        return null;
    }

    /**
     * Метод, реализующий команду add
     *
     * @param worker добавляемый объект
     */
    public void add(Worker worker) throws Exception {
        worker = storage.generateId(worker);
        storage.put(worker);
        changesMade = true;
    }

    /**
     * Метод, реализующий команду update
     *
     * @param id     ID обновляемого объекта
     * @param worker новый объект коллекции
     */
    public void update(long id, Worker worker) {
        removeById(id);
        worker.setId(id);
        storage.put(worker);
        changesMade = true;
    }

    /**
     * Метод, реализующий команду removeById
     *
     * @param id ID удаляемого объекта
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
        changesMade = true;
    }

    /**
     * Метод, реализующий команду clear
     */
    public void clear() {
        storage.clear();
        changesMade = true;
    }

    /**
     * Метод, реализующий команду save
     *
     * @throws IOException в случае ошибки ввода/вывода
     */
    public void save() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(StorageInteraction.originPath));
        String keyLine = "id" + returnSeparator() + "name" + returnSeparator() + "x" + returnSeparator() +
                "y" + returnSeparator() + "salary" + returnSeparator() + "endDate" + returnSeparator() +
                "creationDate" + returnSeparator() + "position" + returnSeparator() + "status" + returnSeparator() +
                "organization" + returnSeparator() + "orgType" + returnSeparator() + "annualTurnover" + returnSeparator()
                + "street" + returnSeparator() + "postalCode" + "\n";
        printWriter.write(keyLine);
        HashSet<Worker> collection = storage.getCollection();
        for (Worker w : collection) {
            printWriter.write(w.getId() + returnSeparator());
            printWriter.write(w.getName() + returnSeparator());
            printWriter.write(w.getCoordinateX() + returnSeparator());
            printWriter.write(w.getCoordinateY() + returnSeparator());
            printWriter.write(w.getSalary() + returnSeparator());
            printWriter.write(w.getEndDateString() + returnSeparator());
            printWriter.write(w.getCreationDateString() + returnSeparator());
            printWriter.write(w.getPositionString() + returnSeparator());
            printWriter.write(w.getStatusString() + returnSeparator());
            printWriter.write(w.getOrganizationNameString() + returnSeparator());
            printWriter.write(w.getOrganizationTypeString() + returnSeparator());
            printWriter.write(w.getAnnualTurnoverString() + returnSeparator());
            printWriter.write(w.getAddressStreet() + returnSeparator());
            printWriter.write(w.getAddressZipCode() + "\n");
            printWriter.flush();
        }
        changesMade = false;
    }

    /**
     * Метод, реализующий команду exit
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Метод, реализующий команду addIfMin
     *
     * @param worker добавляемый объект
     */
    public void addIfMin(Worker worker) {
        HashSet<Worker> workers = storage.getCollection();
        List<Worker> toBeSortedWorkers = new ArrayList<>(workers);
        toBeSortedWorkers.sort(Comparator.comparing(Worker::getSalary));
        if (worker.getSalary() < toBeSortedWorkers.get(0).getSalary()) {
            storage.put(worker);
            changesMade = true;
        }
    }

    /**
     * Метод, реализующий команду removeGreater
     *
     * @param worker объект для сравнения
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
        changesMade = true;
    }

    /**
     * Метод, реализующий команду removeLower
     *
     * @param worker объект для сравнения
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
        changesMade = true;
    }

    /**
     * Метод, реализующий команду countByStatus
     *
     * @param status статус
     * @return Число объектов с указанным статусом
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
     *
     * @return Отсортированное строковое представление коллекции
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
     *
     * @return Список всех уникальных организаций
     */
    public List<String> printUniqueOrganization() {
        List<String> organizations = new ArrayList<>();
        for (Worker w : storage.getCollection()) {
            if (!(w.getOrganization() == null)) {
                String organizationDepiction = w.getOrganization().toString();
                if (!organizations.contains(organizationDepiction))
                    organizations.add(organizationDepiction);
            }
        }
        return organizations;
    }

    /**
     * Метод, возвращающий размер коллекции
     *
     * @return Размер коллекции
     */
    public int getSize() {
        return storage.getCollection().size();
    }

    /**
     * Метод, проверяющий наличие объекта по ID
     *
     * @param id ID для поиска
     * @return True если объект существует, иначе false
     */
    public boolean findById(long id) {
        return storage.getIdList().contains(id);
    }

    /**
     * Метод, проверяюший наличие/отсутсвие несохраненных изменений коллекции
     *
     * @return True - если есть несохраненные изменения, иначе false
     */
    public boolean checkChanges() {
        return changesMade;
    }

    /**
     * Метод, возвращающий разделитель, используемый в оригинальном файле с коллекцией
     *
     * @return Разделитель
     */
    public String returnSeparator() {
        return Character.toString(separator);
    }

    public static void implyChange() {
        changesMade = true;
    }
}
