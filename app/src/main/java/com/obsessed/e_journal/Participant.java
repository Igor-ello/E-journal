package com.obsessed.e_journal;

public class Participant extends Person {
    private int cardID;

    public Participant(String fullName, int phone, int cardID) {
        super(fullName, phone);
        this.cardID = cardID;
    }
}
