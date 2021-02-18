package com.elements;

public enum OrganizationType {
    COMMERCIAL("COMMERCIAL", "Коммерческая"),
    PRIVATE_LIMITED_COMPANY("PRIVATE_LIMITED_COMPANY","Общество с ограниченной ответственностью"),
    OPEN_JOINT_STOCK_COMPANY("OPEN_JOINT_STOCK_COMPANY", "Открытое акционерное общество");

    private String text;
    private String displayText;

    public void setText(String text, String displayText) {
        this.text = text;
        this.displayText = displayText;
    }

    OrganizationType(String text, String displayText) {
        setText(text, displayText);
    }

    @Override
    public String toString() {
        return this.displayText;
    }
}