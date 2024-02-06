package com.obsessed.e_journal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.Participant;
import com.obsessed.e_journal.School.Person;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    Data data;
    Person user;
    ArrayList<EditText> editTextArrayList;
    TextView textView;
    EditText editText;
    LinearLayout linearLayout;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        init();

        if(user instanceof Learner){
            addLearnerFields();
        } else if(user instanceof Parent) {
            addParentFields();
        } else if(user instanceof Teacher) {
            addTeacherFields();
        } else if(user instanceof Employee) {
            addEmployeeFields();
        } else Log.d("MyLog", "The object type is not being processed");

        findViewById(R.id.back).setOnClickListener(view -> {
            finish();
        });
    }

    private void init(){
        data = Data.getInstance();
        user = data.getUser();
        gridLayout = findViewById(R.id.grid);
        editTextArrayList = new ArrayList<>();

        TextView header = findViewById(R.id.header);
        header.setText("Personal account");
    }

    private void addLearnerFields(){
        initParticipant();

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Father: ");
        editText.setText(((Learner) user).getParents().get(0).getFullName());

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Mother: ");
        editText.setText(((Learner) user).getParents().get(1).getFullName());
    }
    private void addParentFields(){
        initPerson();
    }

    private void addTeacherFields(){
        initParticipant();

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Position: ");
        editText.setText(((Teacher) user).getPosition());

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Qualification: ");
        editText.setText(((Teacher) user).getQualification());
    }

    private void addEmployeeFields(){
        initParticipant();

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Position");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    private void initPerson(){
        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Full name: ");
        editText.setText(user.getFullName());

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Phone: ");
        editText.setText(String.valueOf(user.getPhone()));
    }

    private void initParticipant(){
        initPerson();

        createLinearLayout(new String[]{"TextView", "EditText"});
        textView.setText("Card ID: ");
        editText.setText("*****");
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
            } else Log.d("MyLog", "The object type is not being processed");
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
        editText.setEnabled(false);
        editText.setTextColor(Color.BLACK);
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
}