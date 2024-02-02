package com.obsessed.e_journal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;

public class MainActivity extends AppCompatActivity {
    TextView tv_school;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = Data.getInstance();
        tv_school = findViewById(R.id.school);
        tv_school.setText(data.getSchool().toString());
    }
}