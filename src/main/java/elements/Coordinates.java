package elements;

/**
 * Класс Coordinates
 */
public class Coordinates {
    private int x; //Максимальное значение поля: 627
    private long y; //Максимальное значение поля: 990

    /**
     * Стандартный конструктор
     *
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(int x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Переопределенный метод toString для корректного строкового представления координат
     *
     * @return координаты в строковом виде
     */
    @Override
    public String toString() {
        String textx = String.valueOf(x);
        String texty = String.valueOf(y);
        return textx + " " + texty;
    }

    /**
     * Метод, возвращающий отдельно координату X
     *
     * @return координата X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Метод, возвращающий отдельно координату X
     *
     * @return координата Y
     */
    public long getY() {
        return this.y;
    }
}
