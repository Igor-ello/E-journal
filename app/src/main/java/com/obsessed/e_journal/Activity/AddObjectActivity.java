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
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Class;
import com.obsessed.e_journal.School.Elective;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Person;
import com.obsessed.e_journal.School.School;
import com.obsessed.e_journal.School.Section;

import java.util.ArrayList;

public class AddObjectActivity extends AppCompatActivity {
    Data data;
    String object;
    ArrayList<EditText> editTextArrayList;
    ArrayList<EditText> setEditTextArrayList;
    ArrayList<ArrayList<Spinner>> spinnerArrayList;
    ArrayList<Spinner> spinnerArray;
    ArrayList<LinearLayout> linearLayoutArrayList;
    GridLayout gridLayout;
    TextView header, textView;
    EditText editText;
    LinearLayout globalLinearLayout, linearLayout;
    Spinner spinner;
    Button button;
    int countSetButton, countSetLinearLayout;
    ArrayAdapter<String> adapter;

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
                //addObjectToList.addEntrySchoolToList();
            } else if (object.equals("class")) {
                Log.d("MyLog", spinnerArrayList.size() + " ");
                Log.d("MyLog", spinnerArrayList.get(0).size() + " " + spinnerArrayList.get(1).size());
                addObjectToList.addEntryClassToList(
                        editTextArrayList.get(0).getText().toString(),
                        data.getTeachersList().get(spinnerArrayList.get(0).get(0).getSelectedItemPosition()),
                        getSpinnerPosName()
                );
            } else if (object.equals("elective")) {
                addObjectToList.addEntryElectiveToList(
                        editTextArrayList.get(0).getText().toString(),
                        data.getTeachersList().get(spinnerArrayList.get(0).get(0).getSelectedItemPosition()),
                        getSpinnerPosName()
                );
            } else if (object.equals("section")) {
                addObjectToList.addEntrySectionToList(
                        editTextArrayList.get(0).getText().toString(),
                        data.getTeachersList().get(spinnerArrayList.get(0).get(0).getSelectedItemPosition()),
                        getSpinnerPosName()
                );
            } else Log.d("MyLog", "The object type is not being processed");


            Intent intent = new Intent(AddObjectActivity.this, EJournalActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private ArrayList<Learner> getSpinnerPosName(){
        //TODO другие типы
        ArrayList<Learner> learners = new ArrayList<>();
        for (Spinner spinner1: spinnerArrayList.get(1)) {
            learners.add(data.getLearnersList().get(spinner1.getSelectedItemPosition()));
        }
        return learners;
    }

    private void init(){
        data = Data.getInstance();

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
        createArrayListFields("Employees", data.getEmployeesFullNames());
        createArrayListFields("Teachers", data.getTeachersFullNames());
        createArrayListFields("Learners", data.getLearnersFullNames());

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Address: ");
        editText.setInputType(TYPE_CLASS_TEXT);

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Name: ");
        editText.setInputType(TYPE_CLASS_TEXT);

        createArrayListFields("Classes", data.getClassesNames());
        createArrayListFields("Electives", data.getElectivesNames());
        createArrayListFields("Sections", data.getSectionsNames());
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
                spinner = newSpinner(arrayList, 0);
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

        addLearnersAndTeacher();
    }

    private void addElectiveFields(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Academic subject: ");
        editText.setInputType(TYPE_CLASS_TEXT);

        addLearnersAndTeacher();
    }

    private void addSectionFields(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Name: ");
        editText.setInputType(TYPE_CLASS_TEXT);

        addLearnersAndTeacher();
    }

    private void addLearnersAndTeacher(){
        createLinearLayout(new String[]{"TextView", "Spinner"}, data.getTeachersFullNames(), 0);
        textView.setText("Class teacher: ");

        createArrayListFields("Learners", data.getLearnersFullNames());
    }

    //LinearLayout
    private void createLinearLayout(String[] types){
        globalLinearLayout = newLinearLayout();
        for (String type: types) {
            if(type.equals("TextView")){
                globalLinearLayout.addView(newTextView());
            } else if(type.equals("EditText")){
                globalLinearLayout.addView(newEditText());
            } else if(type.equals("Button")){
                globalLinearLayout.addView(newButton());
            } else if(type.equals("LinearLayout")){
                globalLinearLayout.addView(newLinearLayout());
            }
        }

        globalLinearLayout.setId(countSetLinearLayout);
        countSetLinearLayout++;

        linearLayoutArrayList.add(globalLinearLayout);
        gridLayout.addView(globalLinearLayout);
    }

    private void createLinearLayout(String[] types, ArrayList<?> objects, int index){
        globalLinearLayout = newLinearLayout();
        for (String type: types) {
            if(type.equals("TextView")){
                globalLinearLayout.addView(newTextView());
            } else if(type.equals("EditText")){
                globalLinearLayout.addView(newEditText());
            } else if(type.equals("Button")){
                globalLinearLayout.addView(newButton());
            } else if(type.equals("LinearLayout")){
                globalLinearLayout.addView(newLinearLayout());
            } else if(type.equals("Spinner")) {
                globalLinearLayout.addView(newSpinner(objects, index));

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

    private TextView newTextView(){
        textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        return textView;
    }

    private EditText newEditText(){
        editText = new EditText(getApplicationContext());
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        editTextArrayList.add(editText);
        return editText;
    }

    private Button newButton(){
        button = new Button(getApplicationContext());
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        button.setText("Set");

        button.setId(countSetButton);
        countSetButton++;
        button.setTag(String.valueOf(countSetLinearLayout));
        return button;
    }

    private LinearLayout newLinearLayout(){
        linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }

    private Spinner newSpinner(ArrayList<?> objects, int index) {
        Log.d("MyLog", String.valueOf(objects));
        spinner = new Spinner(getApplicationContext());
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, objects);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
        spinner.setSelection(index);
        return spinner;
    }
}
