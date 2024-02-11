package com.obsessed.e_journal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.AddEntryPersonToList;
import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.Data.DataFunctions;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Parent;

import java.util.ArrayList;

public class RegistrationSelectedActivity extends AppCompatActivity {
    Data data;
    DataFunctions dataFunctions;
    AddEntryPersonToList addEntryPersonToList;
    GridLayout gridLayout;
    LinearLayout linearLayout;
    Spinner spinner;
    TextView textView, header;
    ArrayList<EditText> editTextArrayList;
    ArrayList<Spinner> spinnertArrayList;
    ArrayList<Parent> parentArrayList;
    EditText editText;
    String person;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_selected);

        init();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            person = extras.getString("person");

            if(person.equals("Learner")){
                spinnertArrayList = new ArrayList<>();
                addLearnerFields();
            } else if(person.equals("Parent")) {
                addParentFields();
            } else if(person.equals("Teacher")) {
                addTeacherFields();
            } else if(person.equals("Employee")) {
                addEmployeeFields();
            } else Log.d("MyLog", "The object type is not being processed");
        } else Log.d("MyLog", "The message has not been received!");

        findViewById(R.id.save).setOnClickListener(view -> {
            boolean isAllFialdsFillIn = true;
            for (EditText ed: editTextArrayList) {
                if(ed.getText().equals("") || ed.getText().equals(null))
                    isAllFialdsFillIn = false;
            }
            if(isAllFialdsFillIn){
                if(person.equals("Learner")){
                    parentArrayList = new ArrayList<>();
                    parentArrayList.add(data.getParentsList().get(spinnertArrayList.get(0).getSelectedItemPosition()));
                    parentArrayList.add(data.getParentsList().get(spinnertArrayList.get(1).getSelectedItemPosition()));
                    addEntryPersonToList.addEntryLearnersList(editTextArrayList, parentArrayList);
                    data.setUser(data.getLearnersList().get(data.getLearnersList().size()-1));
                } else if(person.equals("Parent")) {
                    addEntryPersonToList.addEntryParentsList(editTextArrayList);
                    data.setUser(data.getParentsList().get(data.getParentsList().size()-1));
                } else if(person.equals("Teacher")) {
                    addEntryPersonToList.addEntryTeachersList(editTextArrayList);
                    data.setUser(data.getTeachersList().get(data.getTeachersList().size()-1));
                } else if(person.equals("Employee")) {
                    addEntryPersonToList.addEntryEmpoloyeesList(editTextArrayList);
                    data.setUser(data.getEmpoloyeesList().get(data.getEmpoloyeesList().size()-1));
                } else Log.d("MyLog", "The message has not been received!");

                Intent intent = new Intent(RegistrationSelectedActivity.this, EJournalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
        });
    }

    private void init(){
        data = Data.getInstance();
        dataFunctions = DataFunctions.getInstance();

        addEntryPersonToList = AddEntryPersonToList.getInstance();
        editTextArrayList = new ArrayList<>();
        gridLayout = findViewById(R.id.grid);

        header = findViewById(R.id.header);
        header.setText("Registration");
    }

    private void addLearnerFields(){
        initPerson();

        createLinearLayout(new String[]{"TextView", "Spinner"});
        textView.setText("Отец");

        createLinearLayout(new String[]{"TextView", "Spinner"});
        textView.setText("Мать");
    }
    private void addParentFields(){
        initPerson();
    }

    private void addTeacherFields(){
        initPerson();

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Position");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        createLinearLayout(new String[]{"TextView", "EditText"}); // берём новый textView и editText
        textView.setText("Qualification");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    private void addEmployeeFields(){
        initPerson();

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Position");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    private void initPerson(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Full name");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Phone");
        editText.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    private void createLinearLayout(String[] types){
        newLinearLayout();
        for (String type: types) {
            if(type.equals("TextView")){
                newTextView();
                linearLayout.addView(textView);
            } else if(type.equals("EditText")){
                newEditText();
                linearLayout.addView(editText);
            } else if(type.equals("Spinner")) {
                newSpinner();
                linearLayout.addView(spinner);
            }
        }
        gridLayout.addView(linearLayout);
    }

    private void newTextView(){
        textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    }

    private void newEditText(){
        editText = new EditText(getApplicationContext());
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        editTextArrayList.add(editText);
    }

    private void newLinearLayout(){
        linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
    }

    private void newSpinner() {
        spinner = new Spinner(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, dataFunctions.getParentsFullNames());
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
        spinnertArrayList.add(spinner);
    }
}
