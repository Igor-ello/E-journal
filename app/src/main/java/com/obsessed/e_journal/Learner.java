package com.obsessed.e_journal;

import java.util.ArrayList;

public class Learner extends Participant{
    private ArrayList<Parent> parents;

    public Learner(String fullName, int phone, int cardID, ArrayList<Parent> parents) {
        super(fullName, phone, cardID);
        this.parents = parents;
    }

    public ArrayList<Parent> getParents() {
        return parents;
    }
}
