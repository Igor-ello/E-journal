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
    private final School school;
    private final String[] persons;
    private Person user;
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
        //Parents
        parentsList = new ArrayList<>();
        parentsList.add(new Parent("Иванов Иван Иванович", 9123456789L));
        parentsList.add(new Parent("Петрова Екатерина Алексеевна", 9124567890L));
        parentsList.add(new Parent("Смирнов Сергей Владимирович", 9345678901L));
        parentsList.add(new Parent("Кузнецова Анастасия Петровна", 9346789012L));
        parentsList.add(new Parent("Федоров Дмитрий Павлович", 9567890123L));
        parentsList.add(new Parent("Морозова Людмила Александровна", 9568901234L));
        parentsList.add(new Parent("Егоров Андрей Николаевич", 9789012345L));
        parentsList.add(new Parent("Волкова Ольга Сергеевна", 9780123456L));
        parentsList.add(new Parent("Никитин Максим Алексеевич", 9901234567L));
        parentsList.add(new Parent("Соловьева Татьяна Владимировна", 9902345678L));


        //Learners
        learnersList = new ArrayList<>();
        learnersList.add(new Learner("Иванов Александр Иванович", 9125678901L, 70001, new ArrayList<>(Arrays.asList(parentsList.get(0), parentsList.get(1)))));
        learnersList.add(new Learner("Иванов Артем Иванович", 9126789012L, 70002, new ArrayList<>(Arrays.asList(parentsList.get(0), parentsList.get(1)))));
        learnersList.add(new Learner("Смирнов Денис Сергеевич", 9346789012L, 70003, new ArrayList<>(Arrays.asList(parentsList.get(2), parentsList.get(3)))));
        learnersList.add(new Learner("Федоров Михаил Дмитриевич", 9566789012L, 70004, new ArrayList<>(Arrays.asList(parentsList.get(4), parentsList.get(5)))));
        learnersList.add(new Learner("Егорова Екатерина Андреевна", 9786789012L, 70005, new ArrayList<>(Arrays.asList(parentsList.get(6), parentsList.get(7)))));
        learnersList.add(new Learner("Никитина Виктория Максимовна", 9906789012L, 70006, new ArrayList<>(Arrays.asList(parentsList.get(8), parentsList.get(9)))));

        //Teachers
        teachersList = new ArrayList<>();
        teachersList.add(new Teacher("Семенова Анна Васильевна", 9890123456L, 10001, "Учитель математики", "Высшая категория"));
        teachersList.add(new Teacher("Петров Петр Петрович", 9891234567L, 10002, "Учитель физики", "Высшая категория"));
        teachersList.add(new Teacher("Иванова Елена Сергеевна ", 9892345678L, 10003, "Учитель литературы", "Молодой специалист"));

        //Empoloyees
        empoloyeesList = new ArrayList<>();
        empoloyeesList.add(new Employee("Николаева Марина Александровна", 9894567890L, 20001, "Администратор"));
        empoloyeesList.add(new Employee("Сидоров Алексей Владимирович", 9896543210L, 20002, "Уборщик"));
        empoloyeesList.add(new Employee("Кузнецова Татьяна Петровна", 9891234567L, 20003, "Кухарка"));

        //Sections
        sectionsList = new ArrayList<>();
        sectionsList.add(new Section("Маткульт", new ArrayList<>(Arrays.asList(learnersList.get(0), learnersList.get(1), learnersList.get(2), learnersList.get(3))), teachersList.get(0)));

        //Elective
        electivesList = new ArrayList<>();
        electivesList.add(new Elective("Фундаментальная физика", new ArrayList<>(Arrays.asList(learnersList.get(0), learnersList.get(3), learnersList.get(4), learnersList.get(5))), teachersList.get(1)));

        //Class
        classesList = new ArrayList<>();
        classesList.add(new Class("I205", teachersList.get(2), new ArrayList<>(Arrays.asList(learnersList.get(0), learnersList.get(1), learnersList.get(2), learnersList.get(3), learnersList.get(4), learnersList.get(5)))));

        //School
        school = new School(empoloyeesList, teachersList, learnersList,
                "1-я Напрудная ул., дом 13, Москва, 129345",
                "Государственное бюджетное общеобразовательное учреждение города Москвы " +
                        "\"Многопрофильная школа №1955\"",
                classesList, electivesList, sectionsList);

        persons = new String[]{"Learner", "Parent", "Teacher", "Employee"};
        parentArrayList = new ArrayList<>();
    }

    public Learner getLearnerByID(int ID) {
        for (Learner learner : learnersList) {
            if (learner.getCardID() == ID) {
                return learner;
            }
        }
        return null;
    }
    public Teacher getTeacherByID(int ID) {
        for (Teacher teacher : teachersList) {
            if (teacher.getCardID() == ID) {
                return teacher;
            }
        }
        return null;
    }
    public Employee getEmployeeByID(int ID) {
        for (Employee employee : empoloyeesList) {
            if (employee.getCardID() == ID) {
                return employee;
            }
        }
        return null;
    }

    public ArrayList<String> getParentsFullNames(){
        ArrayList<String> parents = new ArrayList<>();
        for (Parent parent: parentsList) {
            parents.add(parent.getFullName());
        }
        return parents;
    }

    public Learner getLearnerByParent(Parent parent){
        for (Learner learner: learnersList) {
            if(learner.getParents().get(0) == parent || learner.getParents().get(1) == parent)
                return learner;
        }
        return null;
    }

    public void setParentsByLearnerID(int learner_id){
        for(Learner learner: learnersList) {
            if(learner.getCardID() == learner_id){
                parentArrayList.add(learner.getParents().get(0));
                parentArrayList.add(learner.getParents().get(1));
            }
        }
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

    public School getSchool() {
        return school;
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

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public ArrayList<Parent> getParentArrayList() {
        return parentArrayList;
    }

    public void setParentArrayList(ArrayList<Parent> parentArrayList) {
        this.parentArrayList = parentArrayList;
    }
}
