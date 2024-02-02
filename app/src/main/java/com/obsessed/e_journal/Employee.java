package com.obsessed.e_journal;

public class Employee extends Participant {
    private String position;

    public Employee(String fullName, long phone, int cardID, String position) {
        super(fullName, phone, cardID);
        this.position = position;
    }
}
