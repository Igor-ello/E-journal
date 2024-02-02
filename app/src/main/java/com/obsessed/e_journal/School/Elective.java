package com.obsessed.e_journal.School;

import java.util.ArrayList;

public class Elective {
    private String academicSubject;
    private ArrayList<Learner> learners;
    private Teacher classTeacher;

    public Elective(String academicSubject, ArrayList<Learner> learners, Teacher classTeacher) {
        this.academicSubject = academicSubject;
        this.learners = learners;
        this.classTeacher = classTeacher;
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
