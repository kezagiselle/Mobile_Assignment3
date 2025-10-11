package com.example.assignment3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.MyDatabaseHelper;
import com.example.assignment3.R;
import com.example.assignment3.model.CourseWithFaculty;
import java.util.List;

public class FacultyCoursesActivity extends AppCompatActivity {

    private ListView listViewCourses;
    private TextView tvTitle;
    private MyDatabaseHelper dbHelper;
    private long facultyId;
    private String facultyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courses);

        dbHelper = new MyDatabaseHelper(this);

        listViewCourses = findViewById(R.id.listViewCourses);
        tvTitle = findViewById(R.id.tvTitle);

        facultyId = getIntent().getLongExtra("FACULTY_ID", -1);
        facultyName = getIntent().getStringExtra("FACULTY_NAME");

        tvTitle.setText("Courses in " + facultyName);
        loadCourses();
    }

    private void loadCourses() {
        List<CourseWithFaculty> courses = dbHelper.getCoursesByFaculty(facultyId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1) {
            @Override
            public String getItem(int position) {
                CourseWithFaculty course = courses.get(position);
                return course.getCourseCode() + " - " + course.getCourseName() +
                        "\nCredits: " + course.getCredits();
            }
        };
        listViewCourses.setAdapter(adapter);
    }
}