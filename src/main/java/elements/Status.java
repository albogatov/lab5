package elements;

public enum Status {
    FIRED("FIRED","Уволен"),
    HIRED("HIRED", "Нанят"),
    RECOMMENDED_FOR_PROMOTION("RECOMMENDED_FOR_PROMOTION", "Рекомендован к повышению"),
    REGULAR("REGULAR", "Регулярное выражение"),
    PROBATION("PROBATION", "Стажер");

    private String text;
    private String displayText;

    public void setText(String text, String displayText) {
        this.text = text;
        this.displayText = displayText;
    }

    Status(String text, String displayText) {
        setText(text, displayText);
    }

    @Override
    public String toString() {
        return this.displayText;
    }
}