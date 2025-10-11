
package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.CourseWithFaculty;
import com.example.assignment3.model.Faculty;
import java.util.List;

public class FacultyDetailActivity extends AppCompatActivity {

    private TextView tvFacultyName, tvDeanName, tvStudentCount, tvCourseCount;
    private Button btnViewStudents, btnViewCourses, btnBack;
    private MyDatabaseHelper dbHelper;
    private long facultyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_detail);

        dbHelper = new MyDatabaseHelper(this);

        tvFacultyName = findViewById(R.id.tvFacultyName);
        tvDeanName = findViewById(R.id.tvDeanName);
        tvStudentCount = findViewById(R.id.tvStudentCount);
        tvCourseCount = findViewById(R.id.tvCourseCount);
        btnViewStudents = findViewById(R.id.btnViewStudents);
        btnViewCourses = findViewById(R.id.btnViewCourses);
        btnBack = findViewById(R.id.btnBack);

        facultyId = getIntent().getLongExtra("FACULTY_ID", -1);
        String facultyName = getIntent().getStringExtra("FACULTY_NAME");

        loadFacultyDetails();
        updateCounts();

        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyDetailActivity.this, FacultyStudentsActivity.class);
                intent.putExtra("FACULTY_ID", facultyId);
                intent.putExtra("FACULTY_NAME", facultyName);
                startActivity(intent);
            }
        });

        btnViewCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyDetailActivity.this, FacultyCoursesActivity.class);
                intent.putExtra("FACULTY_ID", facultyId);
                intent.putExtra("FACULTY_NAME", facultyName);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadFacultyDetails() {
        Faculty faculty = dbHelper.getFacultyById(facultyId);
        if (faculty != null) {
            tvFacultyName.setText(faculty.getFacultyName());
            tvDeanName.setText(faculty.getDeanName());
        }
    }

    private void updateCounts() {
        List<StudentWithFaculty> students = dbHelper.getStudentsByFaculty(facultyId);
        List<CourseWithFaculty> courses = dbHelper.getCoursesByFaculty(facultyId);

        tvStudentCount.setText("Students: " + students.size());
        tvCourseCount.setText("Courses: " + courses.size());
    }
}