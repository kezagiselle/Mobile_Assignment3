package com.example.assignment3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
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
        String gender = getIntent().getStringExtra("gender");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        // Display student details
        if (studentId != null && name != null) {
            tvStudentId.setText("Student ID: " + studentId);
            tvName.setText("Name: " + name);
            tvGender.setText("Gender: " + gender);
            tvEmail.setText("Email: " + email);
            tvPhone.setText("Phone: " + phone);

            // Make email clickable
            tvEmail.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            tvEmail.setOnClickListener(v -> {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + email));
                startActivity(emailIntent);
            });

            // Make phone clickable
            tvPhone.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            tvPhone.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            });

        } else {
            tvStudentId.setText("No student details available.");
        }
    }
}
