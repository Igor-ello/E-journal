package com.obsessed.e_journal.School;

public class Teacher extends Participant{
    private String position;
    private String qualification;

    public Teacher(String fullName, long phone, int cardID, String position, String qualification) {
        super(fullName, phone, cardID);
        this.position = position;
        this.qualification = qualification;
    }
}
