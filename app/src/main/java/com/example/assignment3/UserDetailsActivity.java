package com.example.assignment3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {

    TextView tvStudentId, tvName, tvPassword, tvGender, tvEmail, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails); // Make sure this layout has the TextViews

        // Initialize TextViews
        tvStudentId = findViewById(R.id.tvStudentId);
        tvName = findViewById(R.id.tvName);
        tvGender = findViewById(R.id.tvGender);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);

        // Retrieve data from Intent
        String studentId = getIntent().getStringExtra("studentId");
        String name = getIntent().getStringExtra("name");
        String password = getIntent().getStringExtra("password");
        String gender = getIntent().getStringExtra("gender");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        // Display the student details
        if (studentId != null && name != null) {
            tvStudentId.setText("Student ID: " + studentId);
            tvName.setText("Name: " + name);
            tvPassword.setText("Password: " + password);
            tvGender.setText("Gender: " + gender);
            tvEmail.setText("Email: " + email);
            tvPhone.setText("Phone: " + phone);
        } else {
            tvStudentId.setText("No student details available.");
        }
    }
}
