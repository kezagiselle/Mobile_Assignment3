package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Student;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextName, editTextStudentId, editTextEmail, editTextPhone, editTextGender, editTextPassword;
    Button buttonSignUp;

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
        buttonSignUp = findViewById(R.id.buttonSignUp);

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
                    // ✅ Save using LocalStorageHelper
                    LocalStorageHelper storage = new LocalStorageHelper(SignUpActivity.this);

                    // Auto-increment ID (based on saved users count)
                    ArrayList<Student> currentUsers = storage.getAllUsers();
                    int newId = currentUsers.size() + 1;

                    // Create Student object (make sure your Student model matches these fields)
                    Student student = new Student(newId, name, studentId, email, phone, gender, password);

                    // Save student into SharedPreferences
                    storage.addUser(student);

                    Toast.makeText(SignUpActivity.this, "Student Registered Successfully!", Toast.LENGTH_SHORT).show();

                    // Optionally: clear fields after save
                    editTextName.setText("");
                    editTextStudentId.setText("");
                    editTextEmail.setText("");
                    editTextPhone.setText("");
                    editTextGender.setText("");
                    editTextPassword.setText("");
                }
            }
        });
    }
}
