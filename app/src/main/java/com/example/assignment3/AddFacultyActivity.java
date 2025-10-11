package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddFacultyActivity extends AppCompatActivity {

    private EditText etFacultyName, etDeanName;
    private Button btnSaveFaculty, btnCancel;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        dbHelper = new MyDatabaseHelper(this);

        etFacultyName = findViewById(R.id.etFacultyName);
        etDeanName = findViewById(R.id.etDeanName);
        btnSaveFaculty = findViewById(R.id.btnSaveFaculty);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFaculty();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveFaculty() {
        String facultyName = etFacultyName.getText().toString().trim();
        String deanName = etDeanName.getText().toString().trim();

        if (facultyName.isEmpty() || deanName.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.addFaculty(facultyName, deanName);

        if (result != -1) {
            Toast.makeText(this, "Faculty added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to add faculty", Toast.LENGTH_SHORT).show();
        }
    }
}