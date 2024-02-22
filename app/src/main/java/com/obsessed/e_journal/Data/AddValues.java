package com.obsessed.e_journal.Data;

import com.obsessed.e_journal.School.Class;
import com.obsessed.e_journal.School.Elective;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.School;
import com.obsessed.e_journal.School.Section;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;
import java.util.Arrays;

public class AddValues {
    private static volatile AddValues uniqueAddValues;
    ArrayList<Parent> parentsList;
    ArrayList<Learner> learnersList;
    ArrayList<Teacher> teachersList;
    ArrayList<Employee> empoloyeesList;
    ArrayList<Section> sectionsList;
    ArrayList<Elective> electivesList;
    ArrayList<Class> classesList;
    ArrayList<School> schoolsList;


    public static AddValues getInstance() {
        if (uniqueAddValues == null) {
            synchronized (AddValues.class) {
                if (uniqueAddValues == null) {
                    uniqueAddValues = new AddValues();
                }
            }
        }
        return uniqueAddValues;
    }

    private AddValues(){
        parentsList = getParents();
        learnersList = getLearners();
        teachersList = getTeachers();
        empoloyeesList = getEmployees();
        sectionsList = getSections();
        electivesList = getElectives();
        classesList = getClasses();
        schoolsList = getSchools();
    }

    private ArrayList<Parent> getParents(){
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

        return parentsList;
    }

    private ArrayList<Learner> getLearners(){
        learnersList = new ArrayList<>();

        learnersList.add(new Learner("Иванов Александр Иванович", 9125678901L, 70001, new ArrayList<>(Arrays.asList(parentsList.get(0), parentsList.get(1)))));
        learnersList.add(new Learner("Иванов Артем Иванович", 9126789012L, 70002, new ArrayList<>(Arrays.asList(parentsList.get(0), parentsList.get(1)))));
        learnersList.add(new Learner("Смирнов Денис Сергеевич", 9346789012L, 70003, new ArrayList<>(Arrays.asList(parentsList.get(2), parentsList.get(3)))));
        learnersList.add(new Learner("Федоров Михаил Дмитриевич", 9566789012L, 70004, new ArrayList<>(Arrays.asList(parentsList.get(4), parentsList.get(5)))));
        learnersList.add(new Learner("Егорова Екатерина Андреевна", 9786789012L, 70005, new ArrayList<>(Arrays.asList(parentsList.get(6), parentsList.get(7)))));
        learnersList.add(new Learner("Никитина Виктория Максимовна", 9906789012L, 70006, new ArrayList<>(Arrays.asList(parentsList.get(8), parentsList.get(9)))));

        return learnersList;
    }

    private ArrayList<Teacher> getTeachers(){
        teachersList = new ArrayList<>();
        teachersList.add(new Teacher("Семенова Анна Васильевна", 9890123456L, 10001, "Учитель математики", "Высшая категория"));
        teachersList.add(new Teacher("Петров Петр Петрович", 9891234567L, 10002, "Учитель физики", "Высшая категория"));
        teachersList.add(new Teacher("Иванова Елена Сергеевна ", 9892345678L, 10003, "Учитель литературы", "Молодой специалист"));

        return teachersList;
    }

    private ArrayList<Employee> getEmployees(){
        empoloyeesList = new ArrayList<>();
        empoloyeesList.add(new Employee("********* ******* ********", 9999999999L, 1, "Admin"));
        empoloyeesList.add(new Employee("Николаева Марина Александровна", 9894567890L, 20001, "Администратор"));
        empoloyeesList.add(new Employee("Сидоров Алексей Владимирович", 9896543210L, 20002, "Уборщик"));
        empoloyeesList.add(new Employee("Кузнецова Татьяна Петровна", 9891234567L, 20003, "Кухарка"));

        return empoloyeesList;
    }

    private ArrayList<Section> getSections(){
        sectionsList = new ArrayList<>();
        sectionsList.add(new Section("Волейбол", new ArrayList<>(Arrays.asList(learnersList.get(2),
                learnersList.get(3), learnersList.get(4),
                learnersList.get(5))), teachersList.get(2)));
        sectionsList.add(new Section("Борьба", new ArrayList<>(Arrays.asList(learnersList.get(0),
                learnersList.get(1), learnersList.get(2),
                learnersList.get(3))), teachersList.get(1)));

        return sectionsList;
    }

    private ArrayList<Elective> getElectives(){
        electivesList = new ArrayList<>();
        electivesList.add(new Elective("Фундаментальная физика", new ArrayList<>(Arrays.asList(
                learnersList.get(0), learnersList.get(3), learnersList.get(4),
                learnersList.get(5))), teachersList.get(1)));
        electivesList.add(new Elective("Маткульт", new ArrayList<>(Arrays.asList(
                learnersList.get(0), learnersList.get(3), learnersList.get(4),
                learnersList.get(5))), teachersList.get(0)));
        electivesList.add(new Elective("Теория движения", new ArrayList<>(Arrays.asList(
                learnersList.get(0), learnersList.get(3), learnersList.get(4),
                learnersList.get(5))), teachersList.get(2)));

        return electivesList;
    }

    private ArrayList<Class> getClasses(){
        classesList = new ArrayList<>();
        classesList.add(new Class("I205", teachersList.get(2), new ArrayList<>(Arrays.asList(
                learnersList.get(0), learnersList.get(1), learnersList.get(2),
                learnersList.get(3), learnersList.get(4), learnersList.get(5)))));

        return classesList;
    }

    private ArrayList<School> getSchools(){
        schoolsList = new ArrayList<>();
        schoolsList.add( new School(empoloyeesList, teachersList, learnersList,
                "1-я Напрудная ул., дом 13, Москва, 129345",
                "Государственное бюджетное общеобразовательное учреждение города Москвы " +
                        "\"Многопрофильная школа №1955\"",
                classesList, electivesList, sectionsList));
        return schoolsList;
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
}
