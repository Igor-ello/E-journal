package com.obsessed.e_journal.Data;

import com.obsessed.e_journal.School.Class;
import com.obsessed.e_journal.School.Elective;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.Person;
import com.obsessed.e_journal.School.School;
import com.obsessed.e_journal.School.Section;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private static volatile Data uniqueData;
    private final ArrayList<Parent> parentsList;
    private final ArrayList<Learner> learnersList;
    private final ArrayList<Teacher> teachersList;
    private final ArrayList<Employee> empoloyeesList;
    private final ArrayList<Section> sectionsList;
    private final ArrayList<Elective> electivesList;
    private final ArrayList<Class> classesList;
    private final ArrayList<School> schoolsList;
    private final String[] persons;
    private Person user;
    private AddValues addValues;
    private ArrayList<Parent> parentArrayList;


    public static Data getInstance() {
        if (uniqueData == null) {
            synchronized (Data.class) {
                if (uniqueData == null) {
                    uniqueData = new Data();
                }
            }
        }
        return uniqueData;
    }

    private Data(){
        addValues = AddValues.getInstance();

        //Parents
        parentsList = new ArrayList<>();
        parentsList.addAll(addValues.getParentsList());

        //Learners
        learnersList = new ArrayList<>();
        learnersList.addAll(addValues.getLearnersList());

        //Teachers
        teachersList = new ArrayList<>();
        teachersList.addAll(addValues.getTeachersList());

        //Empoloyees
        empoloyeesList = new ArrayList<>();
        empoloyeesList.addAll(addValues.getEmpoloyeesList());

        //Sections
        sectionsList = new ArrayList<>();
        sectionsList.addAll(addValues.getSectionsList());

        //Elective
        electivesList = new ArrayList<>();
        electivesList.addAll(addValues.getElectivesList());

        //Class
        classesList = new ArrayList<>();
        classesList.addAll(addValues.getClassesList());

        //School
        schoolsList = new ArrayList<>();
        schoolsList.addAll(addValues.getSchoolsList());

        persons = new String[]{"Learner", "Parent", "Teacher", "Employee"};
        parentArrayList = new ArrayList<>();
    }


    public ArrayList<Parent> getParentsList() {
        return parentsList;
    }

    public ArrayList<Learner> getLearnersList() {
        return learnersList;
    }

    public ArrayList<Teacher> getTeachersList() {
        return teachersList;
    }

    public ArrayList<Employee> getEmpoloyeesList() {
        return empoloyeesList;
    }

    public ArrayList<Section> getSectionsList() {
        return sectionsList;
    }

    public ArrayList<Elective> getElectivesList() {
        return electivesList;
    }

    public ArrayList<Class> getClassesList() {
        return classesList;
    }

    public ArrayList<School> getSchoolsList() {
        return schoolsList;
    }

    public String[] getPersons() {
        return persons;
    }

    // Add new entry
    public void addEntryLearnersList(Learner learner) {
        learnersList.add(learner);
    }

    public void addEntryParentsList(Parent parent) {
        parentsList.add(parent);
    }

    public void addEntryTeachersList(Teacher teacher) {
        teachersList.add(teacher);
    }

    public void addEntryEmpoloyeesList(Employee employee) {
        empoloyeesList.add(employee);
    }

    public void addEntrySchoolList(School school) {
        schoolsList.add(school);
    }

    public void addEntryClassList(Class cls) {
        classesList.add(cls);
    }

    public void addEntryElectiveList(Elective elective) {
        electivesList.add(elective);
    }

    public void addEntrySectionList(Section section) {
        sectionsList.add(section);
    }

    //User
    public Person getUser() {
        return user;
    }
    public void setUser(Person user) {
        this.user = user;
    }

    //Account
    public ArrayList<Parent> getParentArrayList() {
        return parentArrayList;
    }
    public void setParentArrayList(ArrayList<Parent> parentArrayList) {
        this.parentArrayList = parentArrayList;
    }
}
