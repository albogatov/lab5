package utils;

import elements.OrganizationType;
import elements.Position;
import elements.Status;
import interaction.UserInterface;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValueVerificationTool {

    public static boolean verifyName(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if (!value.chars().allMatch(Character::isLetter)) {
            if (interactionMode) {
                ui.displayMessage("Имя должно состоять только из букв");
            } else {
                throw new InterruptedIOException();
            }
            return false;
        } else return true;
    }

    public static boolean verifySalary(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            if (interactionMode)
                ui.displayMessage("Оклад должен состоять из цифр");
            else
                throw new InterruptedIOException();
            return false;
        }
        return true;
    }

    public static boolean verifyDate(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
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

    public static boolean verifyIntValue(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if(value == null && nullable)
            return true;
        else {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                if(interactionMode)
                    ui.displayMessage("Поле должно быть числом");
                else
                    throw new InterruptedIOException();
                return false;
            }
        }
    }

    public static boolean verifyLongValue(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if(value == null && nullable)
            return true;
        else {
            try {
                Long.parseLong(value);
                return true;
            } catch (NumberFormatException e) {
                if(interactionMode)
                    ui.displayMessage("Поле должно быть числом");
                else
                    throw new InterruptedIOException();
                return false;
            }
        }
    }

    public static boolean verifyStatus(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if(!(value == null)) {
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

    public static boolean verifyPosition(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if(!(value == null)) {
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

    public static boolean verifyOrgType(String value, boolean interactionMode, UserInterface ui, boolean nullable) throws IOException {
        if(!(value == null)) {
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
