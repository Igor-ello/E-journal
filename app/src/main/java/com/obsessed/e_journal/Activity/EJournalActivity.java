package com.obsessed.e_journal.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.Data.DataFunctions;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.Person;
import com.obsessed.e_journal.School.Teacher;

public class EJournalActivity extends AppCompatActivity {
    Data data;
    DataFunctions dataFunctions;
    Person user;
    TextView textView;
    LinearLayout linearLayout;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_journal);

        init();

        if(user instanceof Employee){
            initEmployee();
            if(((Employee) user).getPosition().equals("Admin") &&
                    ((Employee) user).getCardID() == data.getEmpoloyeesList().get(0).getCardID()) {
                LinearLayout linearLayout = findViewById(R.id.adminConsole);
                linearLayout.setVisibility(View.VISIBLE);
            }
        } else if(user instanceof Learner){
            initLearner();
        } else if(user instanceof Parent) {
            initParent();
        } else if(user instanceof Teacher){
            initTeacher();
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
        dataFunctions = DataFunctions.getInstance();
        user = data.getUser();

        TextView header = findViewById(R.id.header);
        header.setText("EJournal");
        gridLayout = findViewById(R.id.grid);
    }


    private void startObjectIntent(String string){
        Intent intent = new Intent(EJournalActivity.this, AddObjectActivity.class);
        intent.putExtra("object", string);
        startActivity(intent);
    }

    private void initLearner(){
        newLinearLayout();
        textView = newTextView("Your schools");
        textView = newTextView();
        textView.setText(dataFunctions.getSchoolsByLearnerID(((Learner) user).getCardID()));

        newLinearLayout();
        textView = newTextView("Your classes");
        textView = newTextView();
        textView.setText(dataFunctions.getClassesByLearnerID(((Learner) user).getCardID()));

        newLinearLayout();
        textView = newTextView("Your electives");
        textView = newTextView();
        textView.setText(dataFunctions.getElectivesByLearnerID(((Learner) user).getCardID()));

        newLinearLayout();
        textView = newTextView("Your sections");
        textView = newTextView();
        textView.setText(dataFunctions.getSectionsByLearnerID(((Learner) user).getCardID()));
    }

    private void initParent() {
        newLinearLayout();
        textView = newTextView("Nothing, your are parent");
    }

    private void initTeacher() {
        newLinearLayout();
        textView = newTextView("Your classes");
        textView = newTextView();
        textView.setText(dataFunctions.getClassesByTeacherID(((Teacher) user).getCardID()));

        newLinearLayout();
        textView = newTextView("Your electives");
        textView = newTextView();
        textView.setText(dataFunctions.getElectivesByTeacherID(((Teacher) user).getCardID()));

        newLinearLayout();
        textView = newTextView("Your sections");
        textView = newTextView();
        textView.setText(dataFunctions.getSectionsByTeacherID(((Teacher) user).getCardID()));
    }

    private void initEmployee(){
        newLinearLayout();
        textView = newTextView("Nothing, your are employee");
    }

    private TextView newTextView(){
        textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        linearLayout.addView(textView);
        return textView;
    }

    private TextView newTextView(String text){
        textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanString);

        linearLayout.addView(textView);
        return textView;
    }

    private void newLinearLayout(){
        linearLayout = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 10, 0, 0);

        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        gridLayout.addView(linearLayout);
    }

}
