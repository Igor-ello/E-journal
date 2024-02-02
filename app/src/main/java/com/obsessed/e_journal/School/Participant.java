package com.obsessed.e_journal.School;

public class Participant extends Person {
    private int cardID;

    public Participant(String fullName, long phone, int cardID) {
        super(fullName, phone);
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }
}
