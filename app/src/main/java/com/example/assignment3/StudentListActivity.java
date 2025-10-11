// StudentListActivity.java
package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private ListView listViewStudents;
    private Button btnAddStudent, btnBackToFaculties;
    private MyDatabaseHelper dbHelper;
    private List<StudentWithFaculty> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        dbHelper = new MyDatabaseHelper(this);

        listViewStudents = findViewById(R.id.listViewStudents);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnBackToFaculties = findViewById(R.id.btnBackToFaculties);

        loadStudents();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentListActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        btnBackToFaculties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentListActivity.this, FacultyListActivity.class);
                startActivity(intent);
            }
        });

        listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentWithFaculty selectedStudent = students.get(position);
                Intent intent = new Intent(StudentListActivity.this, StudentDetailActivity.class);
                intent.putExtra("STUDENT_ID", selectedStudent.getId());
                startActivity(intent);
            }
        });
    }

    private void loadStudents() {
        students = dbHelper.getAllStudentsWithFaculty();
        ArrayAdapter<StudentWithFaculty> adapter = new ArrayAdapter<StudentWithFaculty>(this,
                android.R.layout.simple_list_item_1, students) {
            @Override
            public StudentWithFaculty getItem(int position) {
                StudentWithFaculty student = students.get(position);
                return student;
            }
        };
        listViewStudents.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }
}