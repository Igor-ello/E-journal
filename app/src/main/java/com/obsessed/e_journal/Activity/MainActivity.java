package com.obsessed.e_journal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;
import com.obsessed.e_journal.School.Learner;
import com.obsessed.e_journal.School.Parent;

public class MainActivity extends AppCompatActivity {
    TextView tv_learners, tv_parents;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_learners = findViewById(R.id.learners);
        tv_parents = findViewById(R.id.parents);

        data = Data.getInstance();
        for (Learner learner: data.getSectionsList().get(0).getList()) {
            tv_learners.setText(tv_learners.getText() + learner.getFullName() + "\n");
        }
        for (Parent parent: data.getSectionsList().get(0).getListParents()) {
            tv_parents.setText(tv_parents.getText() + parent.getFullName() + "\n");
        }

    }
}