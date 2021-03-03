package app;

import interaction.Storage;
import interaction.StorageInteraction;
import interaction.UserInterface;
import utils.ReadyCSVParser;

import java.io.*;
import java.time.format.DateTimeParseException;

/**
 * Главный класс консольного приложения.
 *
 * @author Alexandr Bogatov
 */

public class Main {
    /**
     * Главный метод.
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
        try {
            if (args[0] == null) {
                userInteraction.displayMessage("Путь к исходным данным не задан");
            } else {
                while (true) {
                    try {
                        if (firstOpening) {
                            char separator = userInteraction.readUnlimitedArgument("Введите разделитель значений в файле", false).charAt(0);
                            dataFile = new File(args[0]);
                            storage = new Storage();
                            interactiveStorage = new StorageInteraction(storage, args[0], separator);
                            ReadyCSVParser.initParser(separator);
                            try {
                                ReadyCSVParser.readWorkers(dataFile, storage.getCollection(), storage);
                            } catch (NullPointerException e) {
                                userInteraction.displayMessage("Данные в файле введены некорректно или указан неверный разделитель значений");
                                PrintWriter pw = new PrintWriter("errorLog.txt");
                                e.printStackTrace(pw);
                                pw.close();
                                System.exit(1);
                            } catch (DateTimeParseException e) {
                                userInteraction.displayMessage("Неверное форматирование дат");
                                PrintWriter pw = new PrintWriter("errorLog.txt");
                                e.printStackTrace(pw);
                                pw.close();
                                System.exit(1);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                userInteraction.displayMessage("Некорректный файл, проверьте наличие пустых строк");
                                PrintWriter pw = new PrintWriter("errorLog.txt");
                                e.printStackTrace(pw);
                                pw.close();
                                System.exit(1);
                            } catch (IllegalArgumentException e) {
                                userInteraction.displayMessage("Данные в файле некорректны");
                                PrintWriter pw = new PrintWriter("errorLog.txt");
                                e.printStackTrace(pw);
                                pw.close();
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
                            line = userInteraction.read().trim();
                            String cmd = line.split(" ")[0];
                            CommandCenter.getInstance().executeCommand(userInteraction, cmd, line, interactiveStorage);
                        } while (userInteraction.hasNextLine());
                    } catch (NullPointerException e) {
                        userInteraction.displayMessage("Такой команды нет, проверьте правильность ввода или посмотрите список команд с помощью help");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        userInteraction.displayMessage("Введенные аргументы не соответсвуют требуемым для выполнения, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (NumberFormatException e) {
                        userInteraction.displayMessage("Неправильно введены числовые данные, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (FileNotFoundException e) {
                        userInteraction.displayMessage("В качестве аргумента указан путь к несуществующему файлу или доступ к файлу закрыт");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (IOException e) {
                        userInteraction.displayMessage("Произошла ошибка ввода/вывода");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (StackOverflowError e) {
                        userInteraction.displayMessage("Дальнейшее исполнение скрипта приведет к рекурсии");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (DateTimeParseException e) {
                        userInteraction.displayMessage("Дата указана неверно, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (IllegalArgumentException e) {
                        userInteraction.displayMessage("Указано неверное значение поля, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (Exception e) {
                        if(e.getMessage().equals("В коллекции достигнуто максимальное количество элементов"))
                            userInteraction.displayMessage("Коллекция уже содержит максимальное число элементов");
                        else {
                            userInteraction.displayMessage("Произошла неизвестная ошибка");
                            PrintWriter pw = new PrintWriter("errorLog.txt");
                            e.printStackTrace(pw);
                            pw.close();
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            userInteraction.displayMessage("Не указан путь изначального файла");
            PrintWriter pw = new PrintWriter("errorLog.txt");
            e.printStackTrace(pw);
            pw.close();
            System.exit(1);
        }
    }
}


