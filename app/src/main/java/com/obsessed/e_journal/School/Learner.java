package com.obsessed.e_journal.School;

import java.util.ArrayList;

public class Learner extends Participant{
    private ArrayList<Parent> parents;

    public Learner(String fullName, long phone, int cardID, ArrayList<Parent> parents) {
        super(fullName, phone, cardID);
        this.parents = parents;
    }

    public ArrayList<Parent> getParents() {
        return parents;
    }

    public String getParentsNames() {
        return parents.get(0).getFullName() + ", " + parents.get(1).getFullName();
    }

}
