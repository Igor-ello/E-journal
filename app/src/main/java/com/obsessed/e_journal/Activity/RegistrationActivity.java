package com.obsessed.e_journal.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.obsessed.e_journal.Data.Data;
import com.obsessed.e_journal.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();

        findViewById(R.id.BtLearner).setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, RegistrationSelectedActivity.class);
            intent.putExtra("person", "Learner");
            startActivity(intent);
        });

        findViewById(R.id.BtParent).setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, RegistrationSelectedActivity.class);
            intent.putExtra("person", "Parent");
            startActivity(intent);
        });

        findViewById(R.id.BtTeacher).setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, RegistrationSelectedActivity.class);
            intent.putExtra("person", "Teacher");
            startActivity(intent);
        });

        findViewById(R.id.BtEmployee).setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, RegistrationSelectedActivity.class);
            intent.putExtra("person", "Employee");
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void init(){
        TextView header = findViewById(R.id.header);
        header.setText("Registration");
    }
}
