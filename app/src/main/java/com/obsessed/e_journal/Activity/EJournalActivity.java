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
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Employee;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;
import com.obsessed.e_journal.School.Person;
import com.obsessed.e_journal.School.Teacher;

import java.util.ArrayList;

public class EJournalActivity extends AppCompatActivity {
    GridLayout gridLearners, gridParents, gridTeachers, gridEmployees;
    Data data;
    Person user;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_journal);

        init();
        if(!(user instanceof Employee)){
            LinearLayout linearLayout = findViewById(R.id.adminConsole);
            linearLayout.setVisibility(View.GONE);
        } else {
            if(!(((Employee) user).getPosition().equals("Admin") &&
                    ((Employee) user).getCardID() == data.getEmpoloyeesList().get(0).getCardID())){
                LinearLayout linearLayout = findViewById(R.id.adminConsole);
                linearLayout.setVisibility(View.GONE);
            }
        }

        initPerson(gridLearners, data.getLearnersList());
        initPerson(gridParents, data.getParentsList());
        initPerson(gridTeachers, data.getTeachersList());
        initPerson(gridEmployees, data.getEmpoloyeesList());

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
            startObjectIntent("sections");
        });
    }

    private void init(){
        data = Data.getInstance();
        user = data.getUser();
        gridLearners = findViewById(R.id.gridLearners);
        gridParents = findViewById(R.id.gridParents);
        gridTeachers = findViewById(R.id.gridTeachers);
        gridEmployees = findViewById(R.id.gridEmployees);
        TextView header = findViewById(R.id.header);
        header.setText("EJournal");
    }

    @SuppressLint("SetTextI18n")
    public void initPerson(GridLayout grid, ArrayList<? extends Person> arrayList) {
        grid.removeAllViews(); // Удаляем все существующие представления в GridLayout (если они есть)
        grid.setColumnCount(1); // Устанавливаем количество столбцов в GridLayout (WIDTH - константа)

        for (Person person : arrayList) {
            addTextView();

            //Выделяем жирным только часть текста
            SpannableString spanString = new SpannableString(person.getFullName() + ", Phone: " + person.getPhone());
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            String info = "", type = "";
            // Проверяем тип персоны
            if (person instanceof Learner) {
                Learner learner = (Learner) person;
                type = "Learner: ";
                info = ", Card ID: " + learner.getCardID() + ", Parents: " + learner.getParentsNames();
            } else if (person instanceof Parent) {
                Parent parent = (Parent) person;
                type = "Parent: ";
            }else if (person instanceof Teacher) {
                Teacher teacher = (Teacher) person;
                type = "Teacher: ";
                info = ", Card ID: " + teacher.getCardID() + ", Position: " + teacher.getPosition() + ", Qualification: " + teacher.getQualification();
            } else if (person instanceof Employee) {
                Employee employee = (Employee) person;
                type = "Employee: ";
                info = ", Card ID: " + employee.getCardID() + ", Position: " + employee.getPosition();
            } else Log.d("MyLog", "The object type is not being processed");
            textView.setText(TextUtils.concat(type + " Full name: ", spanString, info));

            // Устанавливаем размер текста
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

            // Добавляем TextView в GridLayout
            grid.addView(textView);
        }
    }

    private void addTextView(){
        textView = new TextView(getApplicationContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
    }

    private void startObjectIntent(String string){
        Intent intent = new Intent(EJournalActivity.this, AddObjectActivity.class);
        intent.putExtra("object", string);
        startActivity(intent);
    }

}
