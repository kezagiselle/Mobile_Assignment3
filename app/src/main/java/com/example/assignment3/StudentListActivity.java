package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Student;
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
        studentList = new ArrayList<>();

        // Sample data - replace with your actual data retrieval logic
        studentList.add(new Student("John Doe", "S001", "john@email.com", "1234567890", "Male", "password123"));
        studentList.add(new Student("Jane Smith", "S002", "jane@email.com", "0987654321", "Female", "password456"));
        studentList.add(new Student("Bob Johnson", "S003", "bob@email.com", "5551234567", "Male", "password789"));

        // If you have LocalStorageHelper, use:
        // LocalStorageHelper storage = new LocalStorageHelper(this);
        // studentList = storage.getAllStudents();
    }

    private void setupListView() {
        adapter = new StudentAdapter(this, studentList);
        listViewStudents.setAdapter(adapter);

        listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = studentList.get(position);

                // OPTION 1: Navigate to StudentDetail (if you created it)
                Intent intent = new Intent(StudentListActivity.this, StudentDetail.class);

                // Pass all student details via Intent
                intent.putExtra("studentId", selectedStudent.getStudentId());
                intent.putExtra("name", selectedStudent.getName());
                intent.putExtra("email", selectedStudent.getEmail());
                intent.putExtra("phone", selectedStudent.getPhone());
                intent.putExtra("gender", selectedStudent.getGender());

                startActivity(intent);

                // OPTION 2: If StudentDetail doesn't exist, use this instead:
                // showStudentDetailsDialog(selectedStudent);
            }
        });
    }

    // Optional: Method to show details in a dialog if StudentDetail activity doesn't exist
    private void showStudentDetailsDialog(Student student) {
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Student Details")
                .setMessage("ID: " + student.getStudentId() + "\n" +
                           "Name: " + student.getName() + "\n" +
                           "Email: " + student.getEmail() + "\n" +
                           "Phone: " + student.getPhone() + "\n" +
                           "Gender: " + student.getGender())
                .setPositiveButton("OK", null)
                .show();
        */
    }
}