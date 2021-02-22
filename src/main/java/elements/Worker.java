package elements;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс Worker
 */
public class Worker implements Comparable<Worker> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer salary; //Поле не может быть null, Значение поля должно быть больше 0
    private LocalDate endDate; //Поле может быть null
    private Position position; //Поле может быть null
    private Status status; //Поле может быть null
    private Organization organization; //Поле может быть null

    /**
     * Стандартный конструктор
     * @param name - имя рабочего
     * @param coordinates - координаты
     * @param creationDate - дата добавления в базу
     * @param salary - оклад
     * @param endDate - дата расторжения контракта
     * @param position - должность
     * @param status - статус
     * @param organization - организация
     */
    public Worker(String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, Integer salary, java.time.LocalDate endDate, Position position, Status status, Organization organization) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.endDate = endDate;
        this.position = position;
        this.status = status;
        this.organization = organization;
    }

    public Worker(long id, String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, Integer salary, java.time.LocalDate endDate, Position position, Status status, Organization organization) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.endDate = endDate;
        this.position = position;
        this.status = status;
        this.organization = organization;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }

    public String getCreationDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        String formattedString = creationDate.format(formatter);
        return formattedString;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public String getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        if(this.endDate == null)
            return null;
        return this.endDate.format(formatter);
    }

    public Position getPosition() {
        return this.position;
    }

    public Status getStatus() {
        if (this.status == null)
            return null;
        return this.status;
    }

    public String getPositionString() {
        if (this.position == null)
            return null;
        else return this.position.toDisplay();
    }

    public String getStatusString() {
        if (getStatus() == null)
            return null;
        else return this.status.toDisplay();
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public String getOrganizationName() {
        if (getOrganization() == null)
            return null;
        return this.organization.toString();
    }

    public Long getAnnualTurnover() {
        if (organization == null || organization.getAnnualTurnover() == null)
            return null;
        else return organization.getAnnualTurnover();
    }

    public String getOrganizationType() {
        if (organization == null || organization.getOrganizationType() == null)
            return null;
        else return organization.getOrganizationType().toDisplay();
    }

    public String getPostalAddress() {
        if (organization == null || organization.getPostalAddress() == null)
            return null;
        else return organization.getPostalAddress().toString();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void displayWorker() {
        System.out.println("ID рабочего - " + this.getId() + "\n" + "Имя рабочего - " + this.getName() + "\n" +
                "Координаты рабочего - " + this.getCoordinates().toString() + "\n" +
                "Добавлен в базу - " + this.getCreationDateString() + "\n" +
                "Зарплата рабочего - " + this.getSalary().toString() + "\n" +
                "Контракт истекает - " + this.getEndDate() + "\n" +
                "Должность - " + this.getPositionString() + "\n" +
                "Статус - " + this.getStatusString() + "\n" +
                "Организация - " + this.getOrganizationName() + "\n" +
                "Тип организации - " + this.getOrganizationType()+ "\n" +
                "Годовая выручка организации - " + this.getAnnualTurnover()+ "\n" +
                "Адрес организации - " + this.getPostalAddress());
    }
    @Override
    public int compareTo(Worker comparedWorker) {
        return this.getSalary() - comparedWorker.getSalary();
    }
}