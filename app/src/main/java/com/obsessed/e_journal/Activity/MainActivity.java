package com.obsessed.e_journal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;

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
        tv_learners.setText(String.valueOf(data.getSectionsList().get(0).getList().toString()));
        tv_parents.setText(String.valueOf(data.getSectionsList().get(0).getListParents().toString()));
    }
}