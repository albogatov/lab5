package elements;

/**
 * Перечисляемый тип Position
 */
public enum Position {
    LABORER("LABORER", "Разнорабочий"),
    ENGINEER("ENGINEER", "Инженер"),
    CLEANER("CLEANER", "Уборщик");


    private String text;
    private String displayText;
    /**
     * Метод для задания параметров перечисления
     * @param text
     * @param displayText
     */
    public void setText(String text, String displayText) {
        this.text = text;
        this.displayText = displayText;
    }

    /**
     * Стандартный конструктор
     * @param text - перечисление в виде строки
     * @param displayText - перевод для отображения пользователю
     */
    Position(String text, String displayText) {
        setText(text, displayText);
    }
    /**
     * Переопределенный метод, возвращающий понятный для пользователя вид
     * @return - текст
     */
    @Override
    public String toString() {
        return this.displayText;
    }
}

