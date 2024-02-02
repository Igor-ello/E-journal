package com.obsessed.e_journal.School;

public class Person {
    private String fullName;
    private long phone;

    public Person(String fullName, long phone) {
        this.fullName = fullName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", phone=" + phone +
                '}';
    }
}
