package elements;

public class Coordinates {
    private int x; //Максимальное значение поля: 627
    private long y; //Максимальное значение поля: 990

    public Coordinates(int x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String textx = String.valueOf(x);
        String texty = String.valueOf(y);
        return textx + " " + texty;
    }
}
