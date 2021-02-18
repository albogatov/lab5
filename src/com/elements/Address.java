package com.elements;

public class Address {
    private String street; //Поле не может быть null
    private String zipCode; //Поле не может быть null

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }
}