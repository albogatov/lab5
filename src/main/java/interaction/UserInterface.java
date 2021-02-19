package interaction;

import elements.*;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Reader reader;
    private Writer writer;
    private boolean interactionMode;

    public UserInterface(Reader r, Writer w, boolean im) {
        this.reader = r;
        this.writer = w;
        this.interactionMode = im;
        this.scanner = new Scanner(r);
    }

    public String readCommand() {
        return scanner.nextLine().split(" ")[0];
    }

    public String read() {
        return scanner.nextLine();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public void write(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    public void displayMessage(String message) throws IOException {
        write(message + "\n");
    }

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

    public String readNecessaryArgument(String message, long min, long max) throws IOException {
        /**  String line = null;
         while(line == null || Long.parseLong(line) < min || Long.parseLong(line) > max)
         {
         displayMessage(message);
         line = scanner.nextLine();
         line = line.isEmpty() ? null : line;
         }
         return line; */
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

    public Worker readWorker(String[] arguments, int start) throws IOException {
        if (arguments[start].isEmpty())
            throw new InvalidParameterException("Неверный ввод");
        String name = arguments[start]; //Поле не может быть null, Строка не может быть пустой
        java.time.ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        if (arguments[start + 1].isEmpty() || Integer.parseInt(arguments[start + 1]) <= 0)
            throw new InvalidParameterException("Неверный ввод");
        Integer salary = Integer.parseInt(arguments[start + 1]); //Поле не может быть null, Значение поля должно быть больше 0
        java.time.LocalDate endDate;
        String endDateLine = readOtherArgument("Введите дату расторжения контракта (при наличии) в формате (YYYY-MM-DD):");
        if (endDateLine != null)
            endDate = LocalDate.parse(endDateLine, DateTimeFormatter.ISO_LOCAL_DATE);
        else endDate = null;
        int x = Integer.parseInt(readNecessaryArgument("Введите x координату сотрудника:", Integer.MIN_VALUE, 627));
        long y = Long.parseLong(readNecessaryArgument("Введите y координату сотрудника:", Long.MIN_VALUE, 990));
        Coordinates coordinates = new Coordinates(x, y); //Поле не может быть null
        Status status;
        String statusLine = readOtherArgument("Введите статус сотрудника:");
        if (statusLine != null)
            status = Status.valueOf(statusLine.toUpperCase()); //Поле может быть null
        else status = null;
        Position position;
        String positionLine = readOtherArgument("Введите должность сотрудника:");
        if (positionLine != null)
            position = Position.valueOf(positionLine.toUpperCase()); //Поле может быть null
        else position = null;
        String orgName = readOtherArgument("Укажите организацию сотрудника:");
        Organization organization;
        if (orgName != null) {
            Long annualTurnover = Long.parseLong("0"); //Поле может быть null, Значение поля должно быть больше 0
            while (annualTurnover == 0) {
                annualTurnover = Long.parseLong(readOtherArgument("Введите годовую выручку компании:", 1, Long.MAX_VALUE));
            }
            OrganizationType type = OrganizationType.valueOf(readOtherArgument("Введите тип организации:").toUpperCase()); //Поле может быть null
            String street = readNecessaryArgument("Укажите улицу расположения организации:");
            String zipCode = readNecessaryArgument("Укажите почтовый индекс:");
            Address postalAddress = new Address(street, zipCode); //Поле не может быть null
            organization = new Organization(annualTurnover, type, postalAddress, orgName);
        } else organization = null;
        return new Worker(name, coordinates, creationDate, salary, endDate, position, status, organization);
    }

    public String[] reReadArguments(String message) throws IOException {
        displayMessage(message);
        String[] arguments = scanner.nextLine().split(" ");
        return arguments;
    }
}