package com.obsessed.e_journal;

import android.widget.EditText;

import com.obsessed.e_journal.Activity.AddObjectActivity;
import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.School.Class;
import com.obsessed.e_journal.School.Elective;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.School;
import com.obsessed.e_journal.School.Section;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;

public class AddObjectToList extends AddObjectActivity {
    private static volatile AddObjectToList uniqueAddObjectToList;
    private Data data = Data.getInstance();

    public static AddObjectToList getInstance() {
        if(uniqueAddObjectToList == null){
            synchronized (AddObjectToList.class){
                if(uniqueAddObjectToList == null){
                    uniqueAddObjectToList = new AddObjectToList();
                }
            }
        }
        return uniqueAddObjectToList;
    }

    public void addEntrySchoolToList(ArrayList<Employee> employees, ArrayList<Teacher> teachers,
                                     ArrayList<Learner> learners, String address, String name,
                                     ArrayList<Class> classes, ArrayList<Elective> electives,
                                     ArrayList<Section> sections){
        data.addEntrySchoolList(new School(
                employees, teachers, learners, address, name, classes, electives, sections
        ));
    }
    public void addEntryClassToList(String number, Teacher teacher, ArrayList<Learner> arrayList){
        data.addEntryClassList(new Class(
                number, teacher, arrayList
        ));
    }
    public void addEntryElectiveToList(String academicSubject, Teacher teacher, ArrayList<Learner> arrayList){
        data.addEntryElectiveList(new Elective(
                academicSubject, arrayList, teacher
        ));
    }
    public void addEntrySectionToList(String name, Teacher teacher, ArrayList<Learner> arrayList){
        data.addEntrySectionList(new Section(
                name, arrayList, teacher
        ));
    }
}
