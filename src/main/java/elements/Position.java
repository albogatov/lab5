package elements;

public enum Position {
    LABORER("LABORER","Разнорабочий"),
    ENGINEER("ENGINEER", "Инженер"),
    CLEANER("CLEANER", "Уборщик");


    private String text;
    private String displayText;

    public void setText(String text, String displayText) {
        this.text = text;
        this.displayText = displayText;
    }

    Position(String text, String displayText) {
        setText(text, displayText);
    }

    @Override
    public String toString() {
        return this.displayText;
    }
}

