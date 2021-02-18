package com.elements;

public class Organization {
    private Long annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address postalAddress; //Поле не может быть null
    private String name;

    public Organization(Long annualTurnover, OrganizationType type, Address postalAddress, String name) {
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.postalAddress = postalAddress;
        this.name = name;
    }

    @Override
    public String toString() {
        if(this.name == null)
            return null;
        return this.name;
    }
}