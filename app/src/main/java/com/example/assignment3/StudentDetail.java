package com.example.assignment3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

 public class StudentDetail extends AppCompatActivity {

    private TextView textStudentId, textStudentName, textStudentPassword,
            textStudentGender, textStudentEmail, textStudentPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        initializeViews();
        displayStudentDetails();
        setupClickListeners();
    }

    private void initializeViews() {
        textStudentId = findViewById(R.id.textStudentId);
        textStudentName = findViewById(R.id.textStudentName);
        textStudentPassword = findViewById(R.id.textStudentPassword);
        textStudentGender = findViewById(R.id.textStudentGender);
        textStudentEmail = findViewById(R.id.textStudentEmail);
        textStudentPhone = findViewById(R.id.textStudentPhone);
    }

    private void displayStudentDetails() {
        // Get data from Intent
        Intent intent = getIntent();

        String studentId = intent.getStringExtra("STUDENT_ID");
        String studentName = intent.getStringExtra("STUDENT_NAME");
        String studentPassword = intent.getStringExtra("STUDENT_PASSWORD");
        String studentGender = intent.getStringExtra("STUDENT_GENDER");
        String studentEmail = intent.getStringExtra("STUDENT_EMAIL");
        String studentPhone = intent.getStringExtra("STUDENT_PHONE");

        // Display the data
        textStudentId.setText(studentId != null ? studentId : "N/A");
        textStudentName.setText(studentName != null ? studentName : "N/A");
        textStudentPassword.setText(studentPassword != null ? studentPassword : "N/A");
        textStudentGender.setText(studentGender != null ? studentGender : "N/A");
        textStudentEmail.setText(studentEmail != null ? studentEmail : "N/A");
        textStudentPhone.setText(studentPhone != null ? studentPhone : "N/A");
    }

    private void setupClickListeners() {
        // Phone click listener
        textStudentPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = textStudentPhone.getText().toString();
                if (!phoneNumber.equals("N/A")) {
                    // Implicit Intent for dialer
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
                }
            }
        });

        // Email click listener
        textStudentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textStudentEmail.getText().toString();
                if (!email.equals("N/A")) {
                    // Implicit Intent for email
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:" + email));
                    startActivity(intent);
                }
            }
        });
    }
}
