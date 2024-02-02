package com.obsessed.e_journal.School;

public class Participant extends Person {
    private int cardID;

    public Participant(String fullName, long phone, int cardID) {
        super(fullName, phone);
        this.cardID = cardID;
    }
}
