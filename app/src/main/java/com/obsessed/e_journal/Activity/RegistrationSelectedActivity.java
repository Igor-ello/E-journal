package com.obsessed.e_journal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;

public class RegistrationSelectedActivity extends AppCompatActivity {
    GridLayout gridLayout;
    LinearLayout linearLayout;
    TextView textView, header;
    ArrayList<EditText> editTextArrayList;
    EditText editText;
    String person;
    Data data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_selected);

        init();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            person = extras.getString("person");

            if(person.equals("Learner")){
                initParticipant();
            } else if(person.equals("Parent")) {
                initPerson();
            } else if(person.equals("Teacher")) {
                initParticipant();

                updateVariables();
                textView.setText("Position");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextArrayList.add(editText);

                updateVariables(); // берём новый textView и editText
                textView.setText("Qualification");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextArrayList.add(editText);

            } else if(person.equals("Employee")) {
                initParticipant();

                updateVariables(); // берём новый textView и editText
                textView.setText("Position");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editTextArrayList.add(editText);
            }
        } else Log.d("MyLog", "The message has not been received!");

        findViewById(R.id.save).setOnClickListener(view -> {
            boolean flag = true;
            for (EditText ed: editTextArrayList) {
                if(ed.getText().equals("") || ed.getText().equals(null))
                    flag = false;
            }
            if(flag){
                if(person.equals("Learner")){
                    data.addEntryLearnersList(new Learner(editTextArrayList.get(0).getText().toString(),
                            Long.parseLong(editTextArrayList.get(1).getText().toString()),
                            Integer.parseInt(editTextArrayList.get(2).getText().toString()),
                            new ArrayList<>()));
                } else if(person.equals("Parent")) {
                    data.addEntryParentsList(new Parent(editTextArrayList.get(0).getText().toString(),
                            Long.parseLong(editTextArrayList.get(1).getText().toString())));
                } else if(person.equals("Teacher")) {
                    data.addEntryTeachersList(new Teacher(editTextArrayList.get(0).getText().toString(),
                            Long.parseLong(editTextArrayList.get(1).getText().toString()),
                            Integer.parseInt(editTextArrayList.get(2).getText().toString()),
                            editTextArrayList.get(3).getText().toString(),
                           editTextArrayList.get(4).getText().toString()));
                } else if(person.equals("Employee")) {
                    data.addEntryEmpoloyeesList(new Employee(editTextArrayList.get(0).getText().toString(),
                            Long.parseLong(editTextArrayList.get(1).getText().toString()),
                            Integer.parseInt(editTextArrayList.get(2).getText().toString()),
                            editTextArrayList.get(3).getText().toString()));
                } else Log.d("MyLog", "The message has not been received!");

                Intent intent = new Intent(RegistrationSelectedActivity.this, EJournalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        data = Data.getInstance();
        editTextArrayList = new ArrayList<>();
        gridLayout = findViewById(R.id.grid);
        header = findViewById(R.id.header);
        header.setText("Registration");
    }

    private void initPerson(){
        updateVariables(); // берём новый textView и editText
        textView.setText("Full name");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editTextArrayList.add(editText);

        updateVariables();
        textView.setText("Phone");
        editText.setInputType(InputType.TYPE_CLASS_PHONE);
        editTextArrayList.add(editText);
    }

    private void initParticipant(){
        initPerson();

        updateVariables();
        textView.setText("Card ID");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextArrayList.add(editText);
    }

    private void updateVariables(){
        linearLayout = getNewLinearLayout();
        textView = (TextView) linearLayout.getChildAt(0);
        editText = (EditText) linearLayout.getChildAt(1);
        gridLayout.addView(linearLayout);
    }

    private LinearLayout getNewLinearLayout(){
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        EditText editText = new EditText(getApplicationContext());
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        linearLayout.addView(textView, 0);
        linearLayout.addView(editText, 1);
        return linearLayout;
    }
}
