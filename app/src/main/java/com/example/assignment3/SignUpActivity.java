package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Student;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextName, editTextStudentId, editTextEmail, editTextPhone, editTextGender, editTextPassword;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Make sure this layout has all EditTexts

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
                String password = editTextPassword.getText().toString().trim(); // Optional

                if (name.isEmpty() || studentId.isEmpty() || email.isEmpty() || phone.isEmpty() || gender.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Create Student object
                    Student student = new Student(name, studentId, email, phone, gender);

                    // Optional: show a confirmation toast
                    Toast.makeText(SignUpActivity.this, "Student object created for " + student.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
