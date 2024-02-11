package com.obsessed.e_journal.Data;

import com.obsessed.e_journal.School.Class;
import com.obsessed.e_journal.School.Elective;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.Section;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;

public class DataFunctions{
    static volatile DataFunctions uniqueDataFunctions;
    Data data;

    public static DataFunctions getInstance() {
        if (uniqueDataFunctions == null) {
            synchronized (DataFunctions.class) {
                if (uniqueDataFunctions == null) {
                    uniqueDataFunctions = new DataFunctions();
                }
            }
        }
        return uniqueDataFunctions;
    }

    public DataFunctions(){
        data = Data.getInstance();
    }


    //GET BY ID
    public Learner getLearnerByID(int ID) {
        for (Learner learner : data.getLearnersList()) {
            if (learner.getCardID() == ID) {
                return learner;
            }
        }
        return null;
    }
    public Teacher getTeacherByID(int ID) {
        for (Teacher teacher : data.getTeachersList()) {
            if (teacher.getCardID() == ID) {
                return teacher;
            }
        }
        return null;
    }
    public Employee getEmployeeByID(int ID) {
        for (Employee employee : data.getEmpoloyeesList()) {
            if (employee.getCardID() == ID) {
                return employee;
            }
        }
        return null;
    }


    //GET FULL NAMES
    public ArrayList<String> getLearnersFullNames(){
        ArrayList<String> learners = new ArrayList<>();
        for (Learner learner: data.getLearnersList()) {
            learners.add(learner.getFullName());
        }
        return learners;
    }

    public ArrayList<String> getParentsFullNames(){
        ArrayList<String> parents = new ArrayList<>();
        for (Parent parent: data.getParentsList()) {
            parents.add(parent.getFullName());
        }
        return parents;
    }

    public ArrayList<String> getTeachersFullNames(){
        ArrayList<String> teachers = new ArrayList<>();
        for (Teacher teacher: data.getTeachersList()) {
            teachers.add(teacher.getFullName());
        }
        return teachers;
    }

    public ArrayList<String> getEmployeesFullNames(){
        ArrayList<String> employees = new ArrayList<>();
        for (Employee employee: data.getEmpoloyeesList()) {
            employees.add(employee.getFullName());
        }
        return employees;
    }


    //GET NAMES
    public ArrayList<String> getClassesNames(){
        ArrayList<String> classes = new ArrayList<>();
        for (Class class_my: data.getClassesList()) {
            classes.add(class_my.getNumber());
        }
        return classes;
    }

    public ArrayList<String> getSectionsNames(){
        ArrayList<String> sections = new ArrayList<>();
        for (Section section: data.getSectionsList()) {
            sections.add(section.getName());
        }
        return sections;
    }

    public ArrayList<String> getElectivesNames(){
        ArrayList<String> electives = new ArrayList<>();
        for (Elective elective: data.getElectivesList()) {
            electives.add(elective.getAcademicSubject());
        }
        return electives;
    }


    //OVER
    public Learner getLearnerByParent(Parent parent){
        for (Learner learner: data.getLearnersList()) {
            if(learner.getParents().get(0) == parent || learner.getParents().get(1) == parent)
                return learner;
        }
        return null;
    }

    public void setParentsByLearnerID(int learner_id){
        for(Learner learner: data.getLearnersList()) {
            if(learner.getCardID() == learner_id){
                ArrayList<Parent> arrayList = new ArrayList<>();
                arrayList.add(learner.getParents().get(0));
                arrayList.add(learner.getParents().get(1));
                data.setParentArrayList(arrayList);
            }
        }
    }
}
