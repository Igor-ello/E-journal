package com.obsessed.e_journal.School;

import java.util.ArrayList;

public class School {
    private ArrayList<Employee> employees;
    private ArrayList<Teacher> teachers;
    private ArrayList<Learner> learners;
    private String address;
    private String name;
    private ArrayList<Class> classes;
    private ArrayList<Elective> electives;
    private ArrayList<Section> sections;

    public School(ArrayList<Employee> employees, ArrayList<Teacher> teachers, ArrayList<Learner> learners, String address,
                  String name, ArrayList<Class> classes, ArrayList<Elective> electives, ArrayList<Section> sections) {
        this.employees = employees;
        this.teachers = teachers;
        this.learners = learners;
        this.address = address;
        this.name = name;
        this.classes = classes;
        this.electives = electives;
        this.sections = sections;
    }

    public ArrayList<Teacher> getListTeachers(){
        return teachers;
    }
    public ArrayList<Employee> getListEmployee(){
        return employees;
    }
    public ArrayList<Learner> getListLearner(){
        return learners;
    }

    public ArrayList<Object> getListParticipant(){
        ArrayList<Object> participantList = new ArrayList<>();
        participantList.addAll(teachers);
        participantList.addAll(learners);
        participantList.addAll(employees);
        return participantList;
    }

    public ArrayList<Object> getListPersonsInSchool(){
        ArrayList<Object> personList = new ArrayList<>();
        personList.addAll(teachers);
        personList.addAll(learners);
        personList.addAll(employees);

        ArrayList<Parent> parentsList = new ArrayList<>();
        for(Learner learner: learners){
            parentsList.addAll(learner.getParents());
        }
        personList.addAll(parentsList);
        return personList;
    }

    public void getElectronicJournal(){

    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Learner> getLearners() {
        return learners;
    }

    public void setLearners(ArrayList<Learner> learners) {
        this.learners = learners;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public ArrayList<Elective> getElectives() {
        return electives;
    }

    public void setElectives(ArrayList<Elective> electives) {
        this.electives = electives;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "School{" +
                "employees=" + employees +
                ", teachers=" + teachers +
                ", learners=" + learners +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", classes=" + classes +
                ", electives=" + electives +
                ", sections=" + sections +
                '}';
    }
}
