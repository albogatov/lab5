package parser;

import java.io.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import elements.*;
import interaction.Storage;
import com.opencsv.CSVParser;

public class ReadyCSVParser {
    protected static CSVParser parser = new CSVParser();
    protected static HashMap<String, Integer> keySet = new HashMap<>();

    public ReadyCSVParser() {

    }

    /* String [] data = parser.parseLine(inputLine);

      @param file
     * @return
     */

     public static void readKeyLine(String line) throws IOException {
         List<String> keyLineValues = Arrays.asList(parser.parseLine(line));
         for(String s : keyLineValues) {
             keySet.put(s.toLowerCase(), keyLineValues.indexOf(s));
         }
     }

    public static List<String> readLine(String line) throws IOException {
        return Arrays.asList(parser.parseLine(line));
    }

    public static Worker readWorker(String line) throws IOException {
        List<String> values = ReadyCSVParser.readLine(line);
        String name = values.get(keySet.get("name")); //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = new Coordinates(Integer.parseInt(values.get(keySet.get("x"))), Integer.parseInt(values.get(keySet.get("y")))); //Поле не может быть null
        ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Integer salary = Integer.parseInt(values.get(keySet.get("salary"))); //Поле не может быть null, Значение поля должно быть больше 0
        LocalDate endDate = LocalDate.parse(values.get(keySet.get("enddate")));//Поле может быть null
        Position position = Position.valueOf(values.get(keySet.get("position")).toUpperCase()); //Поле может быть null
        Status status = Status.valueOf(values.get(keySet.get("status")).toUpperCase()); //Поле может быть null
        Organization organization = new Organization(Long.parseLong(values.get(keySet.get("annualturnover"))),
                OrganizationType.valueOf(values.get(keySet.get("orgtype")).toUpperCase()),
                new Address(values.get(keySet.get("street")), values.get(keySet.get("postalcode"))),
                values.get(keySet.get("organization")));
        if (name == null || name.equals("") || coordinates == null || salary == null || salary <= 0) {
            System.out.println("Данные неверны");
            return null;
        }
        return new Worker(name, coordinates, creationDate, salary, endDate, position, status, organization);

    }

    public static HashSet<Worker> readWorkers(File file, HashSet<Worker> workers, Storage storage) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        /*
        This method does not properly convert bytes to characters. As of JDK 1.1, the preferred way to read lines of text is via the BufferedReader.readLine() method. Programs that use the DataInputStream class to read lines can be converted to use the BufferedReader class by replacing code of the form:
      DataInputStream d = new DataInputStream(in);
with:
      BufferedReader d           = new BufferedReader(new InputStreamReader(in));
         */
        //DataInputStream dis = new DataInputStream(bis);
        //{orgtype=8, enddate=4, street=10, postalcode=11, organization=7, name=0, x=1, y=2, position=5, salary=3, status=6, annualturnover=9}
        //{orgtype=8, enddate=4, street=10, postalcode=11, organization=7, name=0, x=1, y=2, position=5, salary=3, status=6, annualturnover=9}
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));
        String line;
        ReadyCSVParser.readKeyLine(br.readLine());
        while ((line = br.readLine()) != null) {
            Worker worker = readWorker(line);
            if (worker != null) workers.add(worker);
            storage.generateId(worker);
        }
        return workers;
    }
}
