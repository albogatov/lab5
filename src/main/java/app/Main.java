package app;

import exceptions.MoreArgumentsRequiredException;
import exceptions.NonExistingCommandException;
import interaction.Storage;
import interaction.StorageInteraction;
import interaction.UserInterface;
import parser.ReadyCSVParser;

import java.io.*;
import java.security.InvalidParameterException;

public class Main {

    public static void main(String[] args) throws IOException {
        /* String path = "C:\\Users\\lifep\\IdeaProjects\\Lab5\\dataFile.csv";
         Scanner terminalInput = new Scanner(System.in);
         String filename = terminalInput.nextLine();
         String filePath = path + filename;
         String path = args[0];
         File file = new File(path); // переделать на ввод с аргументами консоли
         HashSet workers = CSVParser.readWorkers(file);
         /** Iterator<Worker> itr = workers.iterator();
         while(itr.hasNext()) {
         Worker w = itr.next();
         System.out.println(w.toString());
         }
         Scanner in = new Scanner(System.in);
         String line = in.nextLine();
         while(!Objects.equals("exit",line)) {
         if(Objects.equals("help", line)) {


         }
         line = in.nextLine();
         } */
        UserInterface userInteraction = new UserInterface(new InputStreamReader(System.in), new OutputStreamWriter(System.out), true);
        boolean firstOpening = true;
        try {
            if (args[0] == null) {
                System.out.println("Путь к исходным данным не задан");
            } else {
                while (true) {
                    try {
                        File dataFile = new File(args[0]);
                        Storage storage = new Storage();
                        StorageInteraction interactiveStorage = new StorageInteraction(storage);
                        try{
                            ReadyCSVParser.readWorkers(dataFile, storage.getCollection(), storage);
                        } catch (NullPointerException e) {
                            userInteraction.displayMessage("Ключевая строка введена неверно");
                            System.exit(0);
                        }
                        if (storage.getCollection().size() < 1) {
                            System.out.println("Пустая коллекция" + "\n");
                            System.exit(0);
                        }
                        // System.out.println(storage.getCollection());
                        if (firstOpening)
                            userInteraction.displayMessage("Введите команду, полный список команд можно получить с помощью команды help.");
                        firstOpening = false;
                        String line;
                        do {
                            line = userInteraction.read();
                            String cmd = line.split(" ")[0];
                            CommandCenter.getInstance().executeCommand(userInteraction, cmd, line, interactiveStorage);
                        } while (userInteraction.hasNextLine());
                    } catch (NonExistingCommandException e) {
                        userInteraction.displayMessage("Такой команды нет, проверьте правильность ввода или посмотрите список команд с помощью help");
                    } catch (MoreArgumentsRequiredException e) {
                        userInteraction.displayMessage("Было введено слишком мало аргументов, перепроверьте ввод");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        userInteraction.displayMessage("Введенные аргументы не соответсвуют требуемым для выполнения");
                    } catch (NumberFormatException e) {
                        userInteraction.displayMessage("Неправильно введены числовые данные");
                    } catch (IOException e) {
                        userInteraction.displayMessage("Ввод некорректен");
                    } catch (InvalidParameterException e) {
                        userInteraction.displayMessage(e.getMessage());
                    } catch (Exception e) {
                        userInteraction.displayMessage("Ох не повезло, не повезло");
                        e.printStackTrace();
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            userInteraction.displayMessage("Не указан путь изначального файла");
        }
    }
}


