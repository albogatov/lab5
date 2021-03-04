package elements;


/**
 * Класс Organization.
 */
public class Organization {
    /**
     * Поле, содержащее годовую выручку. Может быть null, Значение поля должно быть больше 0.
     */
    private final Long annualTurnover;
    /**
     * Поле, содержащее тип организации. Может быть null.
     */
    private final OrganizationType type;
    /**
     * Поле, содержащее адрес. Не может быть null.
     */
    private final Address postalAddress;
    /**
     * Поле, содержащее название организации. Может быть null.
     */
    private final String name;

    /**
     * Стандартный конструктор, задающий основные параметры.
     *
     * @param annualTurnover годовая выручка.
     * @param type           типа организации.
     * @param postalAddress  адрес.
     * @param name           название организации.
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
     * @return Годовая выручка
     */
    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * Метод, который возвращает тип организации
     *
     * @return Тип
     */
    public OrganizationType getOrganizationType() {
        return type;
    }

    /**
     * Метод, который возвращает адрес
     *
     * @return Адрес
     */
    public Address getPostalAddress() {
        return postalAddress;
    }

    /**
     * Метод, возврающий название организации
     *
     * @return Название организации
     */
    public String getOrganizationName() {
        return name;
    }

    /**
     * Метод, который возвращает организацию в строковом представлении
     *
     * @return Организация в строковом представлении
     */
    @Override
    public String toString() {
        return name + " " + type + " " + annualTurnover + " " + postalAddress;
    }
}