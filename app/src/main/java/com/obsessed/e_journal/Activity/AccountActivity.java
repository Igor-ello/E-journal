package com.obsessed.e_journal.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class AccountActivity extends AppCompatActivity {
    Data data;
    Person user;
    TextView tvTitle, tvVariable;
    LinearLayout linearLayout;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        init();

        if(user instanceof Learner){
            initParticipant();
        } else if(user instanceof Parent) {
            initPerson();
        } else if(user instanceof Teacher) {
            initParticipant();

            updateVariables();  // берём новый textView
            tvTitle.setText("Position: ");
            tvVariable.setText(((Teacher) user).getPosition());

            updateVariables();  // берём новый textView
            tvTitle.setText("Qualification: ");
            tvVariable.setText(((Teacher) user).getQualification());

        } else if(user instanceof Employee) {
            initParticipant();

            updateVariables(); // берём новый textView
            tvTitle.setText("Position: ");
            tvVariable.setText(((Employee) user).getPosition());
        } else Log.d("MyLog", "The object type is not being processed");
    }

    private void init(){
        data = Data.getInstance();
        user = data.getUser();
        gridLayout = findViewById(R.id.grid);

        TextView header = findViewById(R.id.header);
        header.setText("Personal account");
    }

    private void initPerson(){
        updateVariables(); // берём новый textView
        tvTitle.setText("Full name: ");
        tvVariable.setText(user.getFullName());

        updateVariables();
        tvTitle.setText("Phone: ");
        tvVariable.setText(String.valueOf(user.getPhone()));
    }

    private void initParticipant(){
        initPerson();

        updateVariables();  // берём новый textView
        tvTitle.setText("Card ID: ");
        tvVariable.setText("*****");
    }

    private void updateVariables(){
        linearLayout = getNewLinearLayout();
        tvTitle = (TextView) linearLayout.getChildAt(0);
        tvVariable = (TextView) linearLayout.getChildAt(1);
        gridLayout.addView(linearLayout);
    }

    private LinearLayout getNewLinearLayout(){
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        TextView textView2 = new TextView(getApplicationContext());
        textView2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        linearLayout.addView(textView, 0);
        linearLayout.addView(textView2, 1);
        return linearLayout;
    }
}