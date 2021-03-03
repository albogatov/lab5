package utils;

import elements.OrganizationType;
import elements.Position;
import elements.Status;
import interaction.UserInterface;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Класс-утилита для проверки значений на соответствие условиям.
 */
public class ValueVerificationTool {
    /**
     * Метод проверки имени рабочего.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifyName(String value, boolean interactionMode, UserInterface ui) throws IOException {
        if (!value.chars().allMatch(Character::isLetter)) {
            if (interactionMode) {
                ui.displayMessage("Имя должно состоять только из букв");
            } else {
                throw new InterruptedIOException();
            }
            return false;
        } else return true;
    }

    /**
     * Метод проверки оклада.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifySalary(String value, boolean interactionMode, UserInterface ui) throws IOException {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            if (interactionMode)
                ui.displayMessage("Оклад должен состоять из цифр");
            else
                throw new InterruptedIOException();
            return false;
        }
    }

    /**
     * Метод проверки даты.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifyDate(String value, boolean interactionMode, UserInterface ui) throws IOException {
        if (!(value == null)) {
            try {
                LocalDate.parse(value);
                return true;
            } catch (DateTimeParseException e) {
                if (interactionMode)
                    ui.displayMessage("Введена дата неверного формата");
                else
                    throw new InterruptedIOException();
                return false;
            }
        } else return true;
    }

    /**
     * Метод проверки числового значения типа int.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @param nullable        может или не можеть быть null.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifyIntValue(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if (value == null && nullable)
            return true;
        else {
            if(value == null)
                return false;
            else {
                try {
                    Integer.parseInt(value);
                    return true;
                } catch (NumberFormatException e) {
                    if (interactionMode)
                        ui.displayMessage("Поле должно быть числом");
                    else
                        throw new InterruptedIOException();
                    return false;
                }
            }
        }
    }

    /**
     * Метод проверки числового значения типа long.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @param nullable        может или не можеть быть null.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifyLongValue(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if (value == null && nullable)
            return true;
        else {
            if(value == null)
                return false;
            else {
                try {
                    Long.parseLong(value);
                    return true;
                } catch (NumberFormatException e) {
                    if (interactionMode)
                        ui.displayMessage("Поле должно быть числом");
                    else
                        throw new InterruptedIOException();
                    return false;
                }
            }
        }
    }

    /**
     * Метод проверки статуса.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifyStatus(String value, boolean interactionMode, UserInterface ui) throws IOException {
        if (!(value == null)) {
            try {
                Status.valueOf(value.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                if (interactionMode)
                    ui.displayMessage("Указано неизвестное значение");
                else
                    throw new InterruptedIOException();
                return false;
            }
        } else return true;
    }

    /**
     * Метод проверки должности.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifyPosition(String value, boolean interactionMode, UserInterface ui) throws IOException {
        if (!(value == null)) {
            try {
                Position.valueOf(value.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                if (interactionMode)
                    ui.displayMessage("Указано неизвестное значение");
                else
                    throw new InterruptedIOException();
                return false;
            }
        } else return true;
    }

    /**
     * Метод проверки типа организации.
     *
     * @param value           строка ввода.
     * @param interactionMode режим взаимодействия.
     * @param ui              объект, через который ведется взаимодействие с пользователем.
     * @return true/false.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static boolean verifyOrgType(String value, boolean interactionMode, UserInterface ui) throws IOException {
        if (!(value == null)) {
            try {
                OrganizationType.valueOf(value.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                if (interactionMode)
                    ui.displayMessage("Указано неизвестное значение");
                else
                    throw new InterruptedIOException();
                return false;
            }
        } else return true;
    }
}
