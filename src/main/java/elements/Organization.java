package elements;

/**
 * Класс Organization
 */
public class Organization {
    private Long annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address postalAddress; //Поле не может быть null
    private String name;

    /**
     * Стандартный конструктор, задающий основные параметры
     *
     * @param annualTurnover - годовая выручка
     * @param type           - типа организации
     * @param postalAddress  - адрес
     * @param name           - название организации
     */
    public Organization(Long annualTurnover, OrganizationType type, Address postalAddress, String name) {
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.postalAddress = postalAddress;
        this.name = name;
    }

    /**
     * Метод, который возвращает годовую выручку
     *
     * @return - годовая выручка
     */
    public Long getAnnualTurnover() {
        if (annualTurnover == null)
            return null;
        else return annualTurnover;
    }

    /**
     * Метод, который возвращает тип организации
     *
     * @return - тип
     */
    public OrganizationType getOrganizationType() {
        if (type == null)
            return null;
        else return type;
    }

    /**
     * Метод, который возвращает адрес
     *
     * @return - адрес
     */
    public Address getPostalAddress() {
        if (postalAddress == null)
            return null;
        else return postalAddress;
    }

    /**
     * Метод, который возвращает имя организации
     *
     * @return - имя организации
     */
    @Override
    public String toString() {
        if (this.name == null)
            return null;
        return this.name;
    }
}