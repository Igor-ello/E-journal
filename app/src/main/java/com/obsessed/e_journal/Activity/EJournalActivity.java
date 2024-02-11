package com.obsessed.e_journal.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.Data.Data;
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

public class EJournalActivity extends AppCompatActivity {
    Data data;
    Person user;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_journal);

        init();

        if(user instanceof Employee){
            if(((Employee) user).getPosition().equals("Admin") &&
                    ((Employee) user).getCardID() == data.getEmpoloyeesList().get(0).getCardID()) {
                LinearLayout linearLayout = findViewById(R.id.adminConsole);
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                LinearLayout linearLayout = findViewById(R.id.adminConsole);
                linearLayout.setVisibility(View.GONE);
            }
        } else if(user instanceof Learner){

        } else if(user instanceof Parent) {

        } else if(user instanceof Teacher){

        } else {
            LinearLayout linearLayout = findViewById(R.id.adminConsole);
            linearLayout.setVisibility(View.GONE);
        }


        findViewById(R.id.exit).setOnClickListener(view -> {
            Intent intent = new Intent(EJournalActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.account).setOnClickListener(view -> {
            Intent intent = new Intent(EJournalActivity.this, AccountActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.tree).setOnClickListener(view -> {
            Intent intent = new Intent(EJournalActivity.this, TreeActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.add_school).setOnClickListener(view -> {
            startObjectIntent("school");
        });

        findViewById(R.id.add_class).setOnClickListener(view -> {
            startObjectIntent("class");
        });

        findViewById(R.id.add_elective).setOnClickListener(view -> {
            startObjectIntent("elective");
        });

        findViewById(R.id.add_sections).setOnClickListener(view -> {
            startObjectIntent("section");
        });
    }

    private void init(){
        data = Data.getInstance();
        user = data.getUser();

        TextView header = findViewById(R.id.header);
        header.setText("EJournal");
    }


    private void startObjectIntent(String string){
        Intent intent = new Intent(EJournalActivity.this, AddObjectActivity.class);
        intent.putExtra("object", string);
        startActivity(intent);
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

}
