package com.example.assignment3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Student;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails); // Make sure this layout exists

        TextView textView = findViewById(R.id.textView);

        // Retrieve Student object from Intent
        Student student = (Student) getIntent().getSerializableExtra("STUDENT");

        if (student != null) {
            textView.setText(
                    "Name: " + student.getName() + "\n" +
                            "ID: " + student.getStudentId() + "\n" +
                            "Email: " + student.getEmail() + "\n" +
                            "Phone: " + student.getPhone() + "\n" +
                            "Gender: " + student.getGender()
            );
        } else {
            textView.setText("No student details available.");
        }
    }
}
