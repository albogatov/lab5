package elements;

/**
 * Перечисляемый тип Status
 */
public enum Status {
    FIRED("FIRED", "Уволен"),
    HIRED("HIRED", "Нанят"),
    RECOMMENDED_FOR_PROMOTION("RECOMMENDED_FOR_PROMOTION", "Рекомендован к повышению"),
    REGULAR("REGULAR", "Регулярное выражение"),
    PROBATION("PROBATION", "Стажер");

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
    Status(String text, String displayText) {
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