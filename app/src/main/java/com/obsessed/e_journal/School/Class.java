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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Teacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Teacher classTeacher) {
        this.classTeacher = classTeacher;
    }

    public ArrayList<Learner> getLearners() {
        return learners;
    }

    public void setLearners(ArrayList<Learner> learners) {
        this.learners = learners;
    }

    @Override
    public String toString() {
        return "Class{" +
                "number='" + number + '\'' +
                ", classTeacher=" + classTeacher +
                ", learners=" + learners +
                '}';
    }
}
