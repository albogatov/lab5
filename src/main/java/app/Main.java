package app;

import interaction.Storage;
import interaction.StorageInteraction;
import interaction.UserInterface;
import parser.ReadyCSVParser;

import java.io.*;
import java.time.format.DateTimeParseException;

/**
 * Главный класс консольного приложения.
 *
 * @author Alexandr Bogatov
 */

public class Main {
    /**
     * Метод, запускающий приложение.
     *
     * @param args путь к изначальному файлу с данными
     * @throws IOException в случае ошибки ввода/вывода
     */
    public static void main(String[] args) throws IOException {
        UserInterface userInteraction = new UserInterface(new InputStreamReader(System.in), new OutputStreamWriter(System.out), true);
        boolean firstOpening = true;
        File dataFile;
        Storage storage;
        StorageInteraction interactiveStorage = null;
        ReadyCSVParser parser;
        try {
            if (args[0] == null) {
                userInteraction.displayMessage("Путь к исходным данным не задан");
            } else {
                while (true) {
                    try {
                        if(firstOpening) {
                            char separator = userInteraction.readNecessaryArgument("Введите разделитель значений в файле").charAt(0);
                            dataFile = new File(args[0]);
                            storage = new Storage();
                            interactiveStorage = new StorageInteraction(storage, args[0], separator);
                            parser = new ReadyCSVParser(separator);
                            try {
                                parser.readWorkers(dataFile, storage.getCollection(), storage);
                            } catch (NullPointerException e) {
                                userInteraction.displayMessage("Данные в файле введены некорректно или указан неверный разделитель значений");
                                e.printStackTrace();
                                System.exit(1);
                            } catch (DateTimeParseException e) {
                                userInteraction.displayMessage("Неверное форматирование дат");
                                System.exit(1);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                userInteraction.displayMessage("Некорректный файл, проверьте наличие пустых строк");
                                System.exit(1);
                            } catch (IllegalArgumentException e) {
                                userInteraction.displayMessage("Данные в файле некорректны");
                                System.exit(1);
                            }
                            if (storage.getCollection().size() < 1) {
                                userInteraction.displayMessage("Пустая коллекция");
                            }
                            userInteraction.displayMessage("Введите команду, полный список команд можно получить с помощью команды help.");
                            firstOpening = false;
                        }
                        String line;
                        do {
                            line = userInteraction.read();
                            String cmd = line.split(" ")[0];
                            CommandCenter.getInstance().executeCommand(userInteraction, cmd, line, interactiveStorage);
                        } while (userInteraction.hasNextLine());
                    } catch (NullPointerException e) {
                        userInteraction.displayMessage("Такой команды нет, проверьте правильность ввода или посмотрите список команд с помощью help");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        userInteraction.displayMessage("Введенные аргументы не соответсвуют требуемым для выполнения, повторите ввод команды");
                    } catch (NumberFormatException e) {
                        userInteraction.displayMessage("Неправильно введены числовые данные, повторите ввод команды");
                    } catch (FileNotFoundException e) {
                        userInteraction.displayMessage("В качестве аргумента указан путь к несуществующему файлу или доступ к файлу закрыт");
                    } catch (IOException e) {
                        userInteraction.displayMessage("Произошла ошибка ввода/вывода");
                    } catch (StackOverflowError e) {
                        userInteraction.displayMessage("Дальнейшее исполнение скрипта приведет к рекурсии");
                    } catch (DateTimeParseException e) {
                        userInteraction.displayMessage("Дата указана неверно, повторите ввод команды");
                    } catch (IllegalArgumentException e) {
                        userInteraction.displayMessage("Указано неверное значение поля, повторите ввод команды");
                    } catch (Exception e) {
                        userInteraction.displayMessage("Ох не повезло, не повезло");
                        e.printStackTrace();
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            userInteraction.displayMessage("Не указан путь изначального файла");
            System.exit(1);
        }
    }
}


