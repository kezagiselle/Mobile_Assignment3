// AddStudentActivity.java
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

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etStudentId, etNames, etEmail, etPhone;
    private Spinner spinnerGender, spinnerFaculty;
    private Button btnSaveStudent, btnCancel;
    private MyDatabaseHelper dbHelper;
    private List<Faculty> faculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        dbHelper = new MyDatabaseHelper(this);

        etStudentId = findViewById(R.id.etStudentId);
        etNames = findViewById(R.id.etNames);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerFaculty = findViewById(R.id.spinnerFaculty);
        btnSaveStudent = findViewById(R.id.btnSaveStudent);
        btnCancel = findViewById(R.id.btnCancel);

        setupSpinners();

        btnSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupSpinners() {
        // Gender spinner
        ArrayList<String> gender = new ArrayList<String>(3);
        gender.add("Male");
        gender.add("Female");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item ,gender){
            @Override
            public String getItem(int position) {
                return gender.get(position);
            }
        };
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        // Faculty spinner
        faculties = dbHelper.getAllFaculties();
        ArrayAdapter<String> facultyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item) {
            @Override
            public String getItem(int position) {
                return faculties.get(position).getFacultyName();
            }
        };
        facultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaculty.setAdapter(facultyAdapter);
    }

    private void saveStudent() {
        String studentId = etStudentId.getText().toString().trim();
        String names = etNames.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (studentId.isEmpty() || names.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Faculty selectedFaculty = (Faculty) spinnerFaculty.getSelectedItem();
        long facultyId = selectedFaculty.getId();

        long result = dbHelper.addStudent(studentId, names, gender, email, phone, facultyId);

        if (result != -1) {
            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
        }
    }
}