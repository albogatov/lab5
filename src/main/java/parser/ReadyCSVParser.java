package parser;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import elements.*;
import interaction.Storage;
import com.opencsv.CSVParser;
import elements.*;

public class ReadyCSVParser {
    protected static CSVParser parser = new CSVParser();
    protected static List<String> lines = new ArrayList<>();

    public ReadyCSVParser() {

    }

    /* String [] data = parser.parseLine(inputLine);

      @param file
     * @return
     */

    /**
     * public static List<String> readKeyLine(File file) {
     * try {
     * BufferedReader reader = new BufferedReader(new FileReader(file));
     * String line = reader.readLine();
     * List<String> keys = Arrays.asList(parser.parseLine(line));
     * for(String s : keys) {
     * switch (s) {
     * case "name":
     * <p>
     * }
     * }
     * return keys;
     * while ((line = reader.readLine()) != null) {
     * lines.add(line);
     * }
     * System.out.println(lines.get(0));
     * }
     * catch (Exception e) {
     * e.printStackTrace();
     * }
     * return null;
     * }
     */

    public static List<String> readLine(String line) throws IOException {
        return Arrays.asList(parser.parseLine(line));
    }

    public static Worker readWorker(String line) throws IOException {
        List<String> values = ReadyCSVParser.readLine(line);
        String name = values.get(0); //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = new Coordinates(Integer.parseInt(values.get(1)), Integer.parseInt(values.get(2))); //Поле не может быть null
        java.time.ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Integer salary = Integer.parseInt(values.get(3)); //Поле не может быть null, Значение поля должно быть больше 0
        // java.time.LocalDate endDate = MyCSVParser.readCSVDate("endDate", line); //Поле может быть null
        Position position = Position.valueOf(values.get(5).toUpperCase()); //Поле может быть null
        Status status = Status.valueOf(values.get(6).toUpperCase()); //Поле может быть null
        Organization organization = new Organization(Long.parseLong(values.get(9)),
                OrganizationType.valueOf(values.get(8).toUpperCase()),
                new Address(values.get(10), values.get(11)),
                values.get(7));
        java.time.LocalDate endDate = null;
        if (name == null || name.equals("") || coordinates == null || salary == null || salary <= 0) {
            System.out.println("Данные неверны");
            return null;
        }
        return new Worker(name, coordinates, creationDate, salary, endDate, position, status, organization);

    }

    public static HashSet<Worker> readWorkers(File file, HashSet<Worker> workers, Storage storage) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);
        int counter = 1;
        String keyLine = dis.readLine();
        while (dis.available() != 0) {
            Worker worker = readWorker(dis.readLine());
            if (worker != null) workers.add(worker);
            storage.generateId(worker);
        }
        return workers;
    }
}
