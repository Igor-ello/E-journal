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

    public String getAcademicSubject() {
        return academicSubject;
    }

    public void setAcademicSubject(String academicSubject) {
        this.academicSubject = academicSubject;
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
        return "Elective{" +
                "academicSubject='" + academicSubject + '\'' +
                ", learners=" + learners +
                ", classTeacher=" + classTeacher +
                '}';
    }
}
