package app;

import exceptions.NonExistingCommandException;
import interaction.Storage;
import interaction.StorageInteraction;
import interaction.UserInterface;
import parser.ReadyCSVParser;

import java.io.*;
import java.time.format.DateTimeParseException;

public class Main {

    public static void main(String[] args) throws IOException {
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
                        try {
                            ReadyCSVParser.readWorkers(dataFile, storage.getCollection(), storage);
                        } catch (NullPointerException e) {
                            userInteraction.displayMessage("Ключевая строка введена неверно");
                            System.exit(0);
                        } catch (DateTimeParseException e) {
                            userInteraction.displayMessage("Неверное форматирование дат");
                            System.exit(0);
                        }
                        if (storage.getCollection().size() < 1) {
                            System.out.println("Пустая коллекция" + "\n");
                            System.exit(0);
                        }
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
                    } catch (ArrayIndexOutOfBoundsException e) {
                        userInteraction.displayMessage("Введенные аргументы не соответсвуют требуемым для выполнения");
                    } catch (NumberFormatException e) {
                        userInteraction.displayMessage("Неправильно введены числовые данные");
                    } catch(FileNotFoundException e) {
                        userInteraction.displayMessage("В качестве аргумента указан путь к несуществующему файлу");
                    } catch (IOException e) {
                        userInteraction.displayMessage("Ввод некорректен");
                    } catch (StackOverflowError e) {
                        userInteraction.displayMessage("Дальнейшее исполнение скрипта приведет к рекурсии");
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


