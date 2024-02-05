package com.obsessed.e_journal.Activity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.R;

public class RegistrationSelectedActivity extends AppCompatActivity {
    GridLayout gridLayout;
    LinearLayout linearLayout;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_selected);

        gridLayout = findViewById(R.id.grid);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String person = extras.getString("person");

            if(person.equals("Learner")){
                initParticipant();
            } else if(person.equals("Parent")) {
                initPerson();
            } else if(person.equals("Teacher")) {
                initParticipant();

                updateVariables();
                textView.setText("Position");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                updateVariables(); // берём новый textView и editText
                textView.setText("Qualification");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

            } else if(person.equals("Employee")) {
                initParticipant();

                updateVariables(); // берём новый textView и editText
                textView.setText("Position");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        } else Log.d("MyLog", "The message has not been received!");
    }

    private void initPerson(){
        updateVariables(); // берём новый textView и editText
        textView.setText("Full name");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        updateVariables();
        textView.setText("Phone");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private void initParticipant(){
        initPerson();

        updateVariables();
        textView.setText("Card ID");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
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
