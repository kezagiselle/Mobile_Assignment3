// AddCourseActivity.java
package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment3.model.Faculty;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

    private EditText etCourseCode, etCourseName, etCredits;
    private Spinner spinnerFaculty;
    private Button btnSaveCourse, btnCancel;
    private MyDatabaseHelper dbHelper;
    private List<Faculty> faculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        dbHelper = new MyDatabaseHelper(this);

        etCourseCode = findViewById(R.id.etCourseCode);
        etCourseName = findViewById(R.id.etCourseName);
        etCredits = findViewById(R.id.etCredits);
        spinnerFaculty = findViewById(R.id.spinnerFaculty);
        btnSaveCourse = findViewById(R.id.btnSaveCourse);
        btnCancel = findViewById(R.id.btnCancel);

        setupFacultySpinner();

        btnSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCourse();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupFacultySpinner() {
        faculties = dbHelper.getAllFaculties();
        ArrayAdapter<Faculty> facultyAdapter = new ArrayAdapter<Faculty>(this,
                android.R.layout.simple_spinner_item, faculties) {
            @Override
            public Faculty getItem(int position) {
                return faculties.get(position);
            }
        };
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaculty.setAdapter(facultyAdapter);
    }

    private void saveCourse() {
        String courseCode = etCourseCode.getText().toString().trim();
        String courseName = etCourseName.getText().toString().trim();
        String creditsStr = etCredits.getText().toString().trim();

        if (courseCode.isEmpty() || courseName.isEmpty() || creditsStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int credits;
        try {
            credits = Integer.parseInt(creditsStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid credits", Toast.LENGTH_SHORT).show();
            return;
        }

        Faculty selectedFaculty = (Faculty) spinnerFaculty.getSelectedItem();
        long facultyId = selectedFaculty.getId();

        long result = dbHelper.addCourse(courseCode, courseName, credits, facultyId);

        if (result != -1) {
            Toast.makeText(this, "Course added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to add course", Toast.LENGTH_SHORT).show();
        }
    }
}