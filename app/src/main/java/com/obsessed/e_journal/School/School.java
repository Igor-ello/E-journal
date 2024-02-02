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

//    public ArrayList<Participant> getListParticipant(){
//        return ;
//    }
//
//    public ArrayList<Person> getListPersonsInSchool(){
//        return ;
//    }

    public void getElectronicJournal(){

    }

}
