package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Student;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextName, editTextStudentId, editTextEmail, editTextPhone, editTextGender, editTextPassword;
    Button buttonSignUp, buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize EditTexts
        editTextName = findViewById(R.id.editTextName);
        editTextStudentId = findViewById(R.id.editTextStudentId);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextGender = findViewById(R.id.editTextGender);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Initialize buttons
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogin = findViewById(R.id.buttonLogin);

        // SignUp logic
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String studentId = editTextStudentId.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String gender = editTextGender.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (name.isEmpty() || studentId.isEmpty() || email.isEmpty() ||
                        phone.isEmpty() || gender.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Save student
                    LocalStorageHelper storage = new LocalStorageHelper(SignUpActivity.this);
                    Student student = new Student(name, studentId, email, phone, gender, password);
                    storage.addStudent(student);

                    Toast.makeText(SignUpActivity.this, "Student Registered Successfully!", Toast.LENGTH_SHORT).show();

                    // OPTION 1: Navigate to StudentListActivity (if it exists)
                    Intent intent = new Intent(SignUpActivity.this, StudentListActivity.class);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    finish();

                    // OPTION 2: Or navigate back to LoginActivity
                    // Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    // startActivity(intent);
                    // finish();
                }
            }
        });

        // Navigate to LoginActivity when login button is clicked
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}