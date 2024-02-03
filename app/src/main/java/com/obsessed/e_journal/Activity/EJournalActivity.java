package com.obsessed.e_journal.Activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_journal);

        data = Data.getInstance();
        gridLearners = findViewById(R.id.gridLearners);
        gridParents = findViewById(R.id.gridParents);
        gridTeachers = findViewById(R.id.gridTeachers);
        gridEmployees = findViewById(R.id.gridEmployees);

        initPerson(gridLearners, data.getLearnersList());
        initPerson(gridParents, data.getParentsList());
        initPerson(gridTeachers, data.getTeachersList());
        initPerson(gridEmployees, data.getEmpoloyeesList());
    }

    @SuppressLint("SetTextI18n")
    public void initPerson(GridLayout grid, ArrayList<? extends Person> arrayList) {
        grid.removeAllViews(); // Удаляем все существующие представления в GridLayout (если они есть)
        grid.setColumnCount(1); // Устанавливаем количество столбцов в GridLayout (WIDTH - константа)

        for (Person person : arrayList) {
            // Создаем новый TextView
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // Проверяем тип персоны
            SpannableString spanString = new SpannableString(person.getFullName() + ", Phone: " + person.getPhone()); //т.к. есть у всех
            spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            String info = "", type = "";
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
            }
            textView.setText(TextUtils.concat(type, spanString, info));

            // Устанавливаем размер текста
            //float textSizePx = getResources().getDimensionPixelSize(R.dimen.text_size); // получаем размер из ресурсов
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); // устанавливаем размер в пикселях

            // Добавляем TextView в GridLayout
            grid.addView(textView);
        }
    }

}
