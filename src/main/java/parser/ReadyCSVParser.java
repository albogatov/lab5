package parser;

import java.io.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import commands.Add;
import elements.*;
import interaction.Storage;
import com.opencsv.CSVParser;

public class ReadyCSVParser {
    protected static CSVParser parser = new CSVParser();
    protected static HashMap<String, Integer> keySet = new HashMap<>();

    public ReadyCSVParser() {

    }

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
        LocalDate endDate;
        if(values.get(keySet.get("enddate")).equals(""))
            endDate = null;
        else endDate = LocalDate.parse(values.get(keySet.get("enddate")));//Поле может быть null
        Position position;
        if(values.get(keySet.get("position")).equals(""))
            position = null;
        else position = Position.valueOf(values.get(keySet.get("position")).toUpperCase()); //Поле может быть null
        Status status;
        if(values.get(keySet.get("status")).equals(""))
            status = null;
        else status = Status.valueOf(values.get(keySet.get("status")).toUpperCase()); //Поле может быть null
        String orgName;
        if(values.get(keySet.get("organization")).equals(""))
            orgName = null;
        else orgName = values.get(keySet.get("organization"));
        Long annualTurnover;
        if(values.get(keySet.get("annualturnover")).equals(""))
            annualTurnover = null;
        else annualTurnover = Long.parseLong(values.get(keySet.get("annualturnover")));
        OrganizationType orgType;
        if(values.get(keySet.get("orgtype")).equals(""))
            orgType = null;
        else orgType = OrganizationType.valueOf(values.get(keySet.get("orgtype")).toUpperCase());
        Address address;
        if(values.get(keySet.get("street")).equals("") || values.get(keySet.get("postalcode")).equals(""))
            address = null;
        else address = new Address(values.get(keySet.get("street")), values.get(keySet.get("postalcode")));
        Organization organization = new Organization(annualTurnover, orgType, address, orgName);
        if (name == null || name.equals("") || coordinates == null || salary == null || salary <= 0) {
            System.out.println("Данные неверны");
            return null;
        }
        return new Worker(name, coordinates, creationDate, salary, endDate, position, status, organization);

    }

    public static HashSet<Worker> readWorkers(File file, HashSet<Worker> workers, Storage storage) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
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
