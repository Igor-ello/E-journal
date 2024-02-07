package com.obsessed.e_journal.Activity;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;

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

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;

public class AddObjectActivity extends AppCompatActivity {
    Data data;
    String object;
    ArrayList<EditText> editTextArrayList;
    ArrayList<Spinner> spinnertArrayList;
    GridLayout gridLayout;
    TextView header, textView;
    EditText editText;
    LinearLayout linearLayout;
    Spinner spinner;
    Button button;

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
        } else if (object.equals("sections")) {
            addSectionsFields();
        } else Log.d("MyLog", "The object type is not being processed");

        findViewById(R.id.back).setOnClickListener(view -> {
            finish();
        });
    }

    private void init(){
        data = Data.getInstance();
        editTextArrayList = new ArrayList<>();
        spinnertArrayList = new ArrayList<>();
        Intent intent = getIntent();
        object = (String) intent.getExtras().get("object");
        gridLayout = findViewById(R.id.grid);

        header = findViewById(R.id.header);
        header.setText("Add " + object);
    }

    private void addSchoolFields(){

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

    private void addSectionsFields(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Name: ");
        editText.setInputType(TYPE_CLASS_TEXT);

        addLearnersAndTeacher();
    }

    private void addLearnersAndTeacher(){
        createLinearLayout(new String[]{"TextView", "Spinner"}, data.getTeachersFullNames());
        textView.setText("Class teacher: ");

        createLinearLayout(new String[]{"TextView", "EditText", "Button"});
        textView.setText("Number of learners: ");
        editText.setInputType(TYPE_CLASS_NUMBER);

        button.setOnClickListener(view -> {
            textView.setText("Learners: ");
            for (int i=0; i < Integer.parseInt(String.valueOf(editText.getText())); i++){
                createLinearLayout(new String[]{"Spinner"}, data.getLearnersFullNames());
            }
        });
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
            } else if(type.equals("Button")){
                newButton();
                linearLayout.addView(button);
            }
        }
        gridLayout.addView(linearLayout);
    }

    private void createLinearLayout(String[] types, ArrayList<?> objects){
        newLinearLayout();
        for (String type: types) {
            if(type.equals("TextView")){
                newTextView();
                linearLayout.addView(textView);
            } else if(type.equals("EditText")){
                newEditText();
                linearLayout.addView(editText);
            } else if(type.equals("Button")){
                newButton();
                linearLayout.addView(button);
            } else if(type.equals("Spinner")) {
                newSpinner(objects);
                linearLayout.addView(spinner);
//                if(objects.get(0) instanceof Teacher
//                        || objects.get(0) instanceof Learner){
//                }
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

    private void newButton(){
        button = new Button(getApplicationContext());
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        button.setText("Set");
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

    private void newSpinner(ArrayList<?> objects) {
        Log.d("MyLog", String.valueOf(objects));
        spinner = new Spinner(getApplicationContext());
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
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
        spinnertArrayList.add(spinner);
    }
}
