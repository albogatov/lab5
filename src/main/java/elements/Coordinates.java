package elements;

/**
 * Класс Coordinates
 */
public class Coordinates {
    /**
     * Поле, содержащее координату X. Максимальное значение - 627
     */
    private int x;
    /**
     * Поле, содержащее координату Y. Максимальное значение - 990
     */
    private long y;

    /**
     * Стандартный конструктор
     *
     * @param x Координата x
     * @param y Координата y
     */
    public Coordinates(int x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Переопределенный метод toString для корректного строкового представления координат
     *
     * @return Координаты в строковом виде
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
     * @return Координата X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Метод, возвращающий отдельно координату X
     *
     * @return Координата Y
     */
    public long getY() {
        return this.y;
    }
}
