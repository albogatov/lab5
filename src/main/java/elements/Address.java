package elements;

/**
 * Класс Address
 */
public class Address {
    private String street; //Поле не может быть null
    private String zipCode; //Поле не может быть null

    /**
     * Стандартный конструктор, в котором задаются обязательные параметры
     *
     * @param street  - улица
     * @param zipCode - почтовый индекс
     */
    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return this.street;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Переопределение метода toString, возвращает параметры объекта только если оба параметра заданы
     *
     * @return - строковое представление адреса
     */
    @Override
    public String toString() {
        if (street == null || zipCode == null)
            return null;
        else return street + " " + zipCode;
    }
}