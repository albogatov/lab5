package elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Перечисляемый тип Status
 */
public enum Status {
    FIRED("FIRED", "Уволен"),
    HIRED("HIRED", "Нанят"),
    RECOMMENDED_FOR_PROMOTION("RECOMMENDED_FOR_PROMOTION", "Рекомендован к повышению"),
    REGULAR("REGULAR", "Бюджетник"),
    PROBATION("PROBATION", "Стажер");

    private String text;
    private String displayText;
    private static List<Status> possibleValues = Arrays.asList(Status.values());

    /**
     * Метод для задания параметров перечисления
     *
     * @param text        - перечисление в виде строки
     * @param displayText - перевод для отображения пользователю
     */
    public void setText(String text, String displayText) {
        this.text = text;
        this.displayText = displayText;
    }

    /**
     * Метод, возвращающий возможные для ввода значения перечисления
     *
     * @return - список значений
     */
    public static List<Status> getPossibleValues() {
        return possibleValues;
    }

    /**
     * Стандартный конструктор
     *
     * @param text        - перечисление в виде строки
     * @param displayText - перевод для отображения пользователю
     */
    Status(String text, String displayText) {
        setText(text, displayText);
    }

    /**
     * Переопределенный метод, возвращающий возможное для ввода значение
     *
     * @return - текст
     */
    @Override
    public String toString() {
        return this.text;
    }

    /**
     * Метод, возвращающий понятное пользователю представление значения перечисления
     * @return - строка
     */
    public String toDisplay() {
        return this.displayText;
    }
}