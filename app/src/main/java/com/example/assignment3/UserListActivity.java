package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private ListView listViewStudents;
    private List<Student> studentList;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        initializeViews();
        setupStudentList();
        setupListView();
    }

    private void initializeViews() {
        listViewStudents = findViewById(R.id.listViewStudents);
    }

    private void setupStudentList() {
        // TODO: Replace with your actual data source (database, shared preferences, etc.)
        studentList = new ArrayList<>();

        // Sample data - replace with your actual data retrieval logic
        studentList.add(new Student("S001", "John Doe", "password123", "Male", "john@email.com", "1234567890"));
        studentList.add(new Student("S002", "Jane Smith", "password456", "Female", "jane@email.com", "0987654321"));
        studentList.add(new Student("S003", "Bob Johnson", "password789", "Male", "bob@email.com", "5551234567"));

        // If you have a database, use something like:
        // studentList = databaseHelper.getAllStudents();
    }

    private void setupListView() {
        adapter = new StudentAdapter(this, studentList);
        listViewStudents.setAdapter(adapter);

        listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = studentList.get(position);

                // Navigate to StudentDetailActivity with Explicit Intent
                Intent intent = new Intent(StudentListActivity.this, StudentDetailActivity.class);

                // Pass all student details via Intent
                intent.putExtra("STUDENT_ID", selectedStudent.getStudentId());
                intent.putExtra("STUDENT_NAME", selectedStudent.getName());
                intent.putExtra("STUDENT_PASSWORD", selectedStudent.getPassword());
                intent.putExtra("STUDENT_GENDER", selectedStudent.getGender());
                intent.putExtra("STUDENT_EMAIL", selectedStudent.getEmail());
                intent.putExtra("STUDENT_PHONE", selectedStudent.getPhone());

                startActivity(intent);
            }
        });
    }
}