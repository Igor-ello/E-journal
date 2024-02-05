package com.obsessed.e_journal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Participant;
import com.obsessed.e_journal.School.Person;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    LinearLayout participantField, parentField;
    EditText et_participant, et_parent;
    EditText editText;
    int positionSelected = -1;
    Data data;
    String[] persons;
    boolean isParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        data = Data.getInstance();
        persons = data.getPersons();

        Spinner spinner = findViewById(R.id.spinner); //Выбор персоны, определённого типа
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, persons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        initFields();

        //Слушатель выбранной персоны
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clearFields(); //очищаем поля (т.к. учитываются переключения)
                positionSelected = position;

                //показ необходимых полей
                if(position == 0 || position == 2 || position == 3){
                    findViewById(R.id.participantField).setVisibility(View.VISIBLE);
                    editText = findViewById(R.id.et_participant);
                } else if(position == 1){
                    findViewById(R.id.parentField).setVisibility(View.VISIBLE);
                    editText = findViewById(R.id.et_parent);
                } else Log.d("MyLog", "Selected field not processed!");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        findViewById(R.id.login).setOnClickListener(view -> {
            if(checkPersonID()) {
                Intent intent = new Intent(LoginActivity.this, EJournalActivity.class);
                startActivity(intent);
                finish();

                //isParent TODO
            } else {
                Toast.makeText(this, "Enter correct card ID, please", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.registration).setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean checkPersonID(){
        boolean isIdCorrect = false;
        isParent = false;

        if(!editText.equals("")){
            ArrayList<? extends Participant> arrayList = new ArrayList<>();
            int id = Integer.parseInt(String.valueOf(editText.getText()));

            //проверка на вход
            switch (positionSelected){
                case 0: {   //Learner
                    arrayList = data.getLearnersList();
                    break;
                }
                case 1: {   //Parent
                    isParent = true;
                    arrayList = data.getLearnersList();
                    break;
                }
                case 2: {   //Teacher
                    arrayList = data.getTeachersList();
                    break;
                }
                case 3: {   //Employee
                    arrayList = data.getEmpoloyeesList();
                    break;
                }
                default:
                    Log.d("MyLog", "Selected field not processed!");
                    break;
            }

            for(int i = 0; i<arrayList.size(); i++){
                if(arrayList.get(i).getCardID() == id)
                    isIdCorrect = true;
            }
        }
        else Toast.makeText(this, "Enter ID, please.", Toast.LENGTH_SHORT).show();

        return isIdCorrect;
    }

    private void initFields(){
        participantField = findViewById(R.id.participantField);
        parentField = findViewById(R.id.parentField);
        et_participant = findViewById(R.id.et_participant);
        et_parent = findViewById(R.id.et_parent);

        TextView t = findViewById(R.id.header);
        t.setText("Login");
    }

    private void clearFields(){
        participantField.setVisibility(View.GONE);
        parentField.setVisibility(View.GONE);
        et_participant.setText("");
        et_parent.setText("");
    }
}
