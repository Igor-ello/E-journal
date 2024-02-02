package com.obsessed.e_journal.School;

import java.util.ArrayList;

public class Class {
    private String number;
    private Teacher classTeacher;
    private ArrayList<Learner> learners;

    public Class(String number, Teacher classTeacher, ArrayList<Learner> learners) {
        this.number = number;
        this.classTeacher = classTeacher;
        this.learners = learners;
    }

    public ArrayList<Learner> getList(){
        return learners;
    }

    public ArrayList<Parent> getListParents(){
        ArrayList<Parent> listParents = new ArrayList<>();
        for(Learner learner: learners){
            listParents.addAll(learner.getParents());
        }
        return listParents;
    }
}
