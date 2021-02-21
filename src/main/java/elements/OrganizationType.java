package elements;

/**
 * Перечисляемый тип OrganizationType
 */
public enum OrganizationType {
    COMMERCIAL("COMMERCIAL", "Коммерческая"),
    PRIVATE_LIMITED_COMPANY("PRIVATE_LIMITED_COMPANY", "Общество с ограниченной ответственностью"),
    OPEN_JOINT_STOCK_COMPANY("OPEN_JOINT_STOCK_COMPANY", "Открытое акционерное общество");

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
    OrganizationType(String text, String displayText) {
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