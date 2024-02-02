package com.obsessed.e_journal.School;

import java.util.ArrayList;

public class Section {
    private String name;
    private ArrayList<Learner> learners;
    private Teacher classTeacher;

    public Section(String name, ArrayList<Learner> learners, Teacher classTeacher) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Learner> getLearners() {
        return learners;
    }

    public void setLearners(ArrayList<Learner> learners) {
        this.learners = learners;
    }

    public Teacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Teacher classTeacher) {
        this.classTeacher = classTeacher;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", learners=" + learners +
                ", classTeacher=" + classTeacher +
                '}';
    }
}
