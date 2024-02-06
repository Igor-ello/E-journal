package com.obsessed.e_journal;

import android.widget.EditText;

import com.obsessed.e_journal.Activity.RegistrationSelectedActivity;
import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;

public class AddEntryPersonToList extends RegistrationSelectedActivity {
    private static volatile AddEntryPersonToList uniqueAddEntryPersonToList;
    private Data data = Data.getInstance();

    public static AddEntryPersonToList getInstance() {
        if(uniqueAddEntryPersonToList == null){
            synchronized (AddEntryPersonToList.class){
                if(uniqueAddEntryPersonToList == null){
                    uniqueAddEntryPersonToList = new AddEntryPersonToList();
                }
            }
        }
        return uniqueAddEntryPersonToList;
    }

    public void addEntryLearnersList(ArrayList<EditText> editTextArrayList){
        data.addEntryLearnersList(new Learner(editTextArrayList.get(0).getText().toString(),
                Long.parseLong(editTextArrayList.get(1).getText().toString()),
                Integer.parseInt(editTextArrayList.get(2).getText().toString()),
                new ArrayList<>()));
    }

    public void addEntryParentsList(ArrayList<EditText> editTextArrayList){
        data.addEntryParentsList(new Parent(editTextArrayList.get(0).getText().toString(),
                Long.parseLong(editTextArrayList.get(1).getText().toString())));
    }

    public void addEntryTeachersList(ArrayList<EditText> editTextArrayList){
        data.addEntryTeachersList(new Teacher(editTextArrayList.get(0).getText().toString(),
                Long.parseLong(editTextArrayList.get(1).getText().toString()),
                Integer.parseInt(editTextArrayList.get(2).getText().toString()),
                editTextArrayList.get(3).getText().toString(),
                editTextArrayList.get(4).getText().toString()));
    }

    public void addEntryEmpoloyeesList(ArrayList<EditText> editTextArrayList){
        data.addEntryEmpoloyeesList(new Employee(editTextArrayList.get(0).getText().toString(),
                Long.parseLong(editTextArrayList.get(1).getText().toString()),
                Integer.parseInt(editTextArrayList.get(2).getText().toString()),
                editTextArrayList.get(3).getText().toString()));
    }
}
