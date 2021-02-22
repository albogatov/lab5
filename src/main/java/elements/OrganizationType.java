package elements;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисляемый тип OrganizationType
 */
public enum OrganizationType {
    COMMERCIAL("COMMERCIAL", "Коммерческая"),
    PRIVATE_LIMITED_COMPANY("PRIVATE_LIMITED_COMPANY", "Общество с ограниченной ответственностью"),
    OPEN_JOINT_STOCK_COMPANY("OPEN_JOINT_STOCK_COMPANY", "Открытое акционерное общество");

    private String text;
    private String displayText;
    private static List<OrganizationType> possibleValues = Arrays.asList(OrganizationType.values());

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
     * @return - список значений
     */
    public static List<OrganizationType> getPossibleValues() {
        return possibleValues;
    }
    /**
     * Стандартный конструктор
     *
     * @param text        - перечисление в виде строки
     * @param displayText - перевод для отображения пользователю
     */
    OrganizationType(String text, String displayText) {
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