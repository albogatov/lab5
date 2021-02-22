package interaction;

import elements.*;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Класс, реализующий взаимодействие с пользователем
 */
public class UserInterface {
    private Scanner scanner;
    private Reader reader;
    private Writer writer;
    private boolean interactionMode;

    /**
     * Стандартный конструктор
     *
     * @param r  - откуда считывать
     * @param w  - куда записывать
     * @param im - режим взаимодействия (true - интерактивный)
     */
    public UserInterface(Reader r, Writer w, boolean im) {
        this.reader = r;
        this.writer = w;
        this.interactionMode = im;
        this.scanner = new Scanner(r);
    }

    /**
     * Метод, считывающий введенную пользователем строку
     *
     * @return - введенная строка
     */
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Метод, проверяющий наличие несчитанных строк
     *
     * @return - true, если они есть, иначе false
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Метод, пишущий сообщение на вывод
     *
     * @param message - сообшение
     * @throws IOException - в случае некорректного ввода
     */
    public void write(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    /**
     * Метод, демонстрирующий сообщение пользователю
     *
     * @param message - сообщение
     * @throws IOException - в случае некорректного ввода
     */
    public void displayMessage(String message) throws IOException {
        write(message + "\n");
    }

    /**
     * Метод, принимающий от пользователя необходимый для ввода аргумент
     *
     * @param message - сообщение для пользователя
     * @return - введенный аргумент
     * @throws IOException - в случае некорректного ввода
     */
    public String readNecessaryArgument(String message) throws IOException {
        String line = null;
        if (interactionMode) {
            while (line == null) {
                displayMessage(message);
                line = scanner.nextLine();
                line = line.isEmpty() ? null : line;
            }
        } else {
            line = scanner.nextLine();
            line = line.isEmpty() ? null : line;
        }
        if (!interactionMode && line == null)
            throw new InvalidParameterException("Скрипт содержит некорректный ввод данных");
        return line;
    }

    /**
     * Метод, принимающий от пользователя необходимый для ввода численный и ограниченный условиями аргумент
     *
     * @param message - сообщение для пользователя
     * @param min     - минимальное значение
     * @param max     - максимальное значение
     * @return - введенный аргумент
     * @throws IOException - в случае некорректного ввода
     */
    public String readNecessaryArgument(String message, long min, long max) throws IOException {
        String line = null;
        if (interactionMode) {
            while (line == null || Long.parseLong(line) < min || Long.parseLong(line) > max) {
                displayMessage(message);
                line = scanner.nextLine();
                line = line.isEmpty() ? null : line;
            }
        } else {
            line = scanner.nextLine();
            line = line.isEmpty() ? null : line;
            if (line == null || Long.parseLong(line) < min || Long.parseLong(line) > max)
                throw new InvalidParameterException("Скрипт содержит некорректный ввод данных");
        }
        return line;
    }

    /**
     * Метод, принимающий от пользователя необязательный для ввода аргумент (возможно значение null)
     *
     * @param message - сообщение для пользователя
     * @return - введенный аргумент
     * @throws IOException - в случае некорректного ввода
     */
    public String readOtherArgument(String message) throws IOException {
        String line = null;
        if (interactionMode) {
            displayMessage(message);
            line = scanner.nextLine();
            line = line.isEmpty() ? null : line;
        } else {
            line = scanner.nextLine();
            line = line.isEmpty() ? null : line;
        }
        return line;
    }

    /**
     * Метод, принимающий от пользователя необязательный для ввода, численный, ограниченный условиями аргумент (возможно значение null)
     *
     * @param message - сообщение для пользователя
     * @param min     - минимальное значение
     * @param max     - максимальное значение
     * @return - введенный аргумнент
     * @throws IOException - в случае некорректного ввода
     */
    public String readOtherArgument(String message, long min, long max) throws IOException {
        String line = null;
        if (interactionMode) {
            do {
                displayMessage(message);
                line = scanner.nextLine();
            } while (Long.parseLong(line) < min || Long.parseLong(line) > max);
            line = line.isEmpty() ? null : line;
        } else {
            line = scanner.nextLine();
            if (Long.parseLong(line) < min || Long.parseLong(line) > max)
                throw new InvalidParameterException("Скрипт содержит некорректный ввод данных");
        }
        return line;
    }

    /**
     * Метод, считывающий сотрудника (объект коллекции) из строки
     *
     * @param arguments - слова строки, введенной пользователем
     * @param start     - номер первого аргумента
     * @return - объект коллекции
     * @throws IOException - в случае некорректного ввода
     */
    public Worker readWorker(String[] arguments, int start) throws IOException {
        if (arguments[start].isEmpty())
            throw new InvalidParameterException("Неверный ввод");
        String name = arguments[start]; //Поле не может быть null, Строка не может быть пустой
        ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        if (arguments[start + 1].isEmpty() || Integer.parseInt(arguments[start + 1]) <= 0)
            throw new InvalidParameterException("Неверный ввод");
        Integer salary = Integer.parseInt(arguments[start + 1]); //Поле не может быть null, Значение поля должно быть больше 0
        LocalDate endDate;
        try {
            String endDateLine = readOtherArgument("Введите дату расторжения контракта (при наличии) в формате (YYYY-MM-DD):");
            if (endDateLine != null)
                endDate = LocalDate.parse(endDateLine, DateTimeFormatter.ISO_LOCAL_DATE);
            else endDate = null;
        } catch (DateTimeParseException e) {
            String endDateLine = readOtherArgument("Допушена ошибка, повторите ввод");
            if (endDateLine != null)
                endDate = LocalDate.parse(endDateLine, DateTimeFormatter.ISO_LOCAL_DATE);
            else endDate = null;
        }
        int x;
        long y;
        try {
            x = Integer.parseInt(readNecessaryArgument("Введите x координату сотрудника:", Integer.MIN_VALUE, 627));
        } catch (NumberFormatException e) {
            x = Integer.parseInt(readNecessaryArgument("Допущена ошибка, повторите ввод", Integer.MIN_VALUE, 627));
        }
        try {
            y = Long.parseLong(readNecessaryArgument("Введите y координату сотрудника:", Long.MIN_VALUE, 990));
        } catch (NumberFormatException e) {
            y = Long.parseLong(readNecessaryArgument("Допущена ошибка, повторите ввод", Long.MIN_VALUE, 990));
        }
        Coordinates coordinates = new Coordinates(x, y); //Поле не может быть null
        Status status;
        try {
            String statusLine = readOtherArgument("Введите статус сотрудника, возможны значения: " + Status.getPossibleValues());
            if (statusLine != null)
                status = Status.valueOf(statusLine.toUpperCase()); //Поле может быть null
            else status = null;
        } catch (IllegalArgumentException e) {
            String statusLine = readOtherArgument("Допущена ошибка, повторите ввод");
            if (statusLine != null)
                status = Status.valueOf(statusLine.toUpperCase()); //Поле может быть null
            else status = null;
        }
        Position position;
        try {
            String positionLine = readOtherArgument("Введите должность сотрудника, возможны значения: " + Position.getPossibleValues());
            if (positionLine != null)
                position = Position.valueOf(positionLine.toUpperCase()); //Поле может быть null
            else position = null;
        } catch (IllegalArgumentException e) {
            String positionLine = readOtherArgument("Допущена ошибка, повторите ввод");
            if (positionLine != null)
                position = Position.valueOf(positionLine.toUpperCase()); //Поле может быть null
            else position = null;
        }
        String orgName = readOtherArgument("Укажите организацию сотрудника:");
        Organization organization;
        if (orgName != null) {
            Long annualTurnover = Long.parseLong("0"); //Поле может быть null, Значение поля должно быть больше 0
            while (annualTurnover == 0) {
                annualTurnover = Long.parseLong(readOtherArgument("Введите годовую выручку компании:", 1, Long.MAX_VALUE));
            }
            OrganizationType type = null;
            try {
                type = OrganizationType.valueOf(readOtherArgument("Введите тип организации, возможны значения: " + OrganizationType.getPossibleValues()).toUpperCase()); //Поле может быть null
            } catch (IllegalArgumentException e) {
                type = OrganizationType.valueOf(readOtherArgument("Допущена ошибка, повторите ввод").toUpperCase()); //Поле может быть null
            }
            String street = readNecessaryArgument("Укажите улицу расположения организации:");
            String zipCode = readNecessaryArgument("Укажите почтовый индекс:");
            Address postalAddress = new Address(street, zipCode); //Поле не может быть null
            organization = new Organization(annualTurnover, type, postalAddress, orgName);
        } else organization = null;
        return new Worker(name, coordinates, creationDate, salary, endDate, position, status, organization);
    }

    /**
     * Метод, принимающий аргументы от пользователя во время выполнения команды
     *
     * @param message - сообщение для пользователя
     * @return - аргументы
     * @throws IOException - в случае некорректного ввода
     */
    public String[] reReadArguments(String message) throws IOException {
        displayMessage(message);
        String[] arguments = scanner.nextLine().split(" ");
        return arguments;
    }
}
