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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
