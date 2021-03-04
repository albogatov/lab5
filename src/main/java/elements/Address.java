package elements;

/**
 * Класс Address.
 */
public class Address {
    /**
     * Поле, содержащее улицу. Не может быть null.
     */
    private final String street;
    /**
     * Поле, содержащее индекс. Не может быть null.
     */
    private final String zipCode;

    /**
     * Стандартный конструктор, в котором задаются обязательные параметры.
     *
     * @param street  улица.
     * @param zipCode почтовый индекс.
     */
    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    /**
     * Метод, возвращающий название улицы.
     *
     * @return название.
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Метод, возвращающий индекс.
     *
     * @return индекс.
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Переопределение метода toString, возвращает параметры объекта только если оба параметра заданы.
     *
     * @return строковое представление адреса.
     */
    @Override
    public String toString() {
        if (street == null || zipCode == null)
            return null;
        else return street + " " + zipCode;
    }
}