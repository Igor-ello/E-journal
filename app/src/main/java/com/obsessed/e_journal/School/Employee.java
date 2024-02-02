package com.obsessed.e_journal.School;

public class Employee extends Participant {
    private String position;

    public Employee(String fullName, long phone, int cardID, String position) {
        super(fullName, phone, cardID);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
