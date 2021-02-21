package elements;

public class Address {
    private String street; //Поле не может быть null
    private String zipCode; //Поле не может быть null

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        if(street.equals(null) || zipCode.equals(null))
            return null;
        else return street + " " + zipCode;
    }
}