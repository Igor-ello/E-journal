package com.obsessed.e_journal.Activity;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.AddObjectToList;
import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.Data.DataFunctions;
import com.obsessed.e_journal.NewElements;
import com.obsessed.e_journal.R;
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

public class AddObjectActivity extends AppCompatActivity {
    Data data;
    DataFunctions dataFunctions;
    NewElements newElements;

    GridLayout gridLayout;
    TextView header, textView;
    EditText editText;
    LinearLayout globalLinearLayout, linearLayout;
    Spinner spinner;
    Button button;
    int countSetButton, countSetLinearLayout;
    String object;

    ArrayList<EditText> editTextArrayList;
    ArrayList<EditText> setEditTextArrayList;
    ArrayList<ArrayList<Spinner>> spinnerArrayList;
    ArrayList<Spinner> spinnerArray;
    ArrayList<LinearLayout> linearLayoutArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);

        init();

        if(object.equals("school")) {
            addSchoolFields();
        } else if (object.equals("class")) {
            addClassFields();
        } else if (object.equals("elective")) {
            addElectiveFields();
        } else if (object.equals("section")) {
            addSectionFields();
        } else Log.d("MyLog", "The object type is not being processed");

        findViewById(R.id.back).setOnClickListener(view -> {
            finish();
        });

        findViewById(R.id.save).setOnClickListener(view -> {
            AddObjectToList addObjectToList = AddObjectToList.getInstance();
            if(object.equals("school")) {
                for (ArrayList<Spinner> lists: spinnerArrayList){
                    for (Spinner list: lists){
                        Log.d("MyLog", list.toString());
                    }
                }
                addObjectToList.addEntrySchoolToList(
                        (ArrayList<Employee>) getSpinnerPosName("Employee", spinnerArrayList.get(0)),
                        (ArrayList<Teacher>) getSpinnerPosName("Teacher", spinnerArrayList.get(1)),
                        (ArrayList<Learner>) getSpinnerPosName("Learner", spinnerArrayList.get(2)),
                        editTextArrayList.get(0).getText().toString(),
                        editTextArrayList.get(1).getText().toString(),
                        (ArrayList<Class>) getSpinnerPosName("Class", spinnerArrayList.get(3)),
                        (ArrayList<Elective>) getSpinnerPosName("Elective", spinnerArrayList.get(4)),
                        (ArrayList<Section>) getSpinnerPosName("Section", spinnerArrayList.get(5))
                );
            } else if (object.equals("class")) {
                addObjectToList.addEntryClassToList(
                        editTextArrayList.get(0).getText().toString(),
                        data.getTeachersList().get(spinnerArrayList.get(0).get(0).getSelectedItemPosition()),
                        (ArrayList<Learner>) getSpinnerPosName("Learner", spinnerArrayList.get(1))
                );
            } else if (object.equals("elective")) {
                addObjectToList.addEntryElectiveToList(
                        editTextArrayList.get(0).getText().toString(),
                        data.getTeachersList().get(spinnerArrayList.get(0).get(0).getSelectedItemPosition()),
                        (ArrayList<Learner>) getSpinnerPosName("Learner", spinnerArrayList.get(1))
                );
            } else if (object.equals("section")) {
                addObjectToList.addEntrySectionToList(
                        editTextArrayList.get(0).getText().toString(),
                        data.getTeachersList().get(spinnerArrayList.get(0).get(0).getSelectedItemPosition()),
                        (ArrayList<Learner>) getSpinnerPosName("Learner", spinnerArrayList.get(1))
                );
            } else Log.d("MyLog", "The object type is not being processed");


            Intent intent = new Intent(AddObjectActivity.this, EJournalActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private ArrayList<?> getSpinnerPosName(String type, ArrayList<Spinner> arrayList){
        if (type.equals("Learner")){
            ArrayList<Person> object = new ArrayList<>();
            for (Spinner spinner1: arrayList) {
                object.add(data.getLearnersList().get(spinner1.getSelectedItemPosition()));
            }
            return object;
        } else if (type.equals("Teacher")) {
            ArrayList<Person> object = new ArrayList<>();
            for (Spinner spinner1: arrayList) {
                object.add(data.getTeachersList().get(spinner1.getSelectedItemPosition()));
            }
            return object;
        } else if (type.equals("Employee")) {
            ArrayList<Person> object = new ArrayList<>();
            for (Spinner spinner1: arrayList) {
                object.add(data.getEmpoloyeesList().get(spinner1.getSelectedItemPosition()));
            }
            return object;
        } else if (type.equals("Class")) {
            ArrayList<Class> object = new ArrayList<>();
            for (Spinner spinner1: arrayList) {
                object.add(data.getClassesList().get(spinner1.getSelectedItemPosition()));
            }
            return object;
        } else if (type.equals("Elective")) {
            ArrayList<Elective> object = new ArrayList<>();
            for (Spinner spinner1: arrayList) {
                object.add(data.getElectivesList().get(spinner1.getSelectedItemPosition()));
            }
            return object;
        } else if (type.equals("Section")) {
            ArrayList<Section> object = new ArrayList<>();
            for (Spinner spinner1: arrayList) {
                object.add(data.getSectionsList().get(spinner1.getSelectedItemPosition()));
            }
            return object;
        } else return null;
    }

    private void init(){
        data = Data.getInstance();
        dataFunctions = DataFunctions.getInstance();
        newElements = new NewElements(this);

        editTextArrayList = new ArrayList<>();
        setEditTextArrayList = new ArrayList<>();
        spinnerArrayList = new ArrayList<>();
        spinnerArray = new ArrayList<>();
        linearLayoutArrayList = new ArrayList<>();

        Intent intent = getIntent();
        object = (String) intent.getExtras().get("object");
        gridLayout = findViewById(R.id.grid);
        countSetButton = 0;
        countSetLinearLayout = 0;

        header = findViewById(R.id.header);
        header.setText("Add " + object);
    }

    private void addSchoolFields(){
        createArrayListFields("Employees", dataFunctions.getEmployeesFullNames());
        createArrayListFields("Teachers", dataFunctions.getTeachersFullNames());
        createArrayListFields("Learners", dataFunctions.getLearnersFullNames());

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Address: ");
        editText.setInputType(TYPE_CLASS_TEXT);
        editTextArrayList.add(editText);


        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Name: ");
        editText.setInputType(TYPE_CLASS_TEXT);
        editTextArrayList.add(editText);

        createArrayListFields("Classes", dataFunctions.getClassesNames());
        createArrayListFields("Electives", dataFunctions.getElectivesNames());
        createArrayListFields("Sections", dataFunctions.getSectionsNames());
    }

    @SuppressLint("SetTextI18n")
    private void createArrayListFields(String text, ArrayList arrayList) {
        createLinearLayout(new String[]{"TextView", "EditText", "Button", "LinearLayout"});
        textView.setText( text + " (max " + arrayList.size() + "): ");
        editText.setInputType(TYPE_CLASS_NUMBER);
        setEditTextArrayList.add(editText);

        button.setOnClickListener(view -> {

            int numberOfSubjects = Integer.parseInt(String.valueOf(setEditTextArrayList.get(view.getId()).getText()));
            if(numberOfSubjects > data.getLearnersList().size())
                numberOfSubjects = data.getLearnersList().size();

            spinnerArray = new ArrayList<>();
            linearLayout = (LinearLayout) linearLayoutArrayList.get(Integer.parseInt((String) view.getTag())).getChildAt(3);
            linearLayout.removeAllViews();
            for (int i = 0; i < numberOfSubjects; i++) {
                spinner = newElements.newSpinner(arrayList, i);
                spinnerArray.add(spinner);
                linearLayout.addView(spinner);
            }
            Log.d("MyLog", "spinnerArrayList.add(spinnerArray);");
            spinnerArrayList.add(spinnerArray);
        });
    }

    private void addClassFields(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Number: ");
        editText.setInputType(TYPE_CLASS_TEXT);
        editTextArrayList.add(editText);

        addLearnersAndTeacher();
    }

    private void addElectiveFields(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Academic subject: ");
        editText.setInputType(TYPE_CLASS_TEXT);
        editTextArrayList.add(editText);

        addLearnersAndTeacher();
    }

    private void addSectionFields(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Name: ");
        editText.setInputType(TYPE_CLASS_TEXT);
        editTextArrayList.add(editText);

        addLearnersAndTeacher();
    }

    private void addLearnersAndTeacher(){
        createLinearLayout(new String[]{"TextView", "Spinner"}, dataFunctions.getTeachersFullNames(), 0);
        textView.setText("Class teacher: ");

        createArrayListFields("Learners", dataFunctions.getLearnersFullNames());
    }

    //LinearLayout
    private void createLinearLayout(String[] types){
        globalLinearLayout = newElements.newLinearLayout();
        for (String type: types) {
            if(type.equals("TextView")){
                textView = newElements.newTextView();
                globalLinearLayout.addView(textView);
            } else if(type.equals("EditText")){
                editText = newElements.newEditText();
                globalLinearLayout.addView(editText);
            } else if(type.equals("Button")){
                button = newElements.newButton();

                button.setText("Set");
                button.setId(countSetButton);
                countSetButton++;
                button.setTag(String.valueOf(countSetLinearLayout));

                globalLinearLayout.addView(button);
            } else if(type.equals("LinearLayout")){
                linearLayout = newElements.newLinearLayout();
                globalLinearLayout.addView(linearLayout);
            }
        }

        globalLinearLayout.setId(countSetLinearLayout);
        countSetLinearLayout++;

        linearLayoutArrayList.add(globalLinearLayout);
        gridLayout.addView(globalLinearLayout);
    }

    private void createLinearLayout(String[] types, ArrayList<?> objects, int index){
        globalLinearLayout = newElements.newLinearLayout();
        for (String type: types) {
            if(type.equals("TextView")){
                textView = newElements.newTextView();
                globalLinearLayout.addView(textView);
            } else if(type.equals("EditText")){
                editText = newElements.newEditText();
                globalLinearLayout.addView(editText);
            } else if(type.equals("Button")){
                button = newElements.newButton();

                button.setText("Set");
                button.setId(countSetButton);
                countSetButton++;
                button.setTag(String.valueOf(countSetLinearLayout));

                globalLinearLayout.addView(button);
            } else if(type.equals("LinearLayout")){
                linearLayout = newElements.newLinearLayout();
                globalLinearLayout.addView(linearLayout);
            } else if(type.equals("Spinner")) {
                spinner = newElements.newSpinner(objects, index);
                globalLinearLayout.addView(spinner);

                spinnerArray = new ArrayList<>();
                spinnerArray.add(spinner);
                spinnerArrayList.add(spinnerArray);
            }
        }

        globalLinearLayout.setId(countSetLinearLayout);
        countSetLinearLayout++;

        linearLayoutArrayList.add(globalLinearLayout);
        gridLayout.addView(globalLinearLayout);
    }
}
