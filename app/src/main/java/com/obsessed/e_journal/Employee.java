package com.obsessed.e_journal;

public class Employee extends Participant {
    private String position;

    public Employee(String fullName, int phone, int cardID, String position) {
        super(fullName, phone, cardID);
        this.position = position;
    }
}
