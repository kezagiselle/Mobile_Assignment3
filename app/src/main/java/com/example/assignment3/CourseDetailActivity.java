
package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment3.model.CourseWithFaculty;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView tvCourseCode, tvCourseName, tvCredits, tvFaculty;
    private Button btnBack;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        dbHelper = new MyDatabaseHelper(this);

        tvCourseCode = findViewById(R.id.tvCourseCode);
        tvCourseName = findViewById(R.id.tvCourseName);
        tvCredits = findViewById(R.id.tvCredits);
        tvFaculty = findViewById(R.id.tvFaculty);
        btnBack = findViewById(R.id.btnBack);

        long courseId = getIntent().getLongExtra("COURSE_ID", -1);
        loadCourseDetails(courseId);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadCourseDetails(long courseId) {
        List<CourseWithFaculty> allCourses = dbHelper.getAllCoursesWithFaculty();
        CourseWithFaculty course = null;

        for (CourseWithFaculty c : allCourses) {
            if (c.getId() == courseId) {
                course = c;
                break;
            }
        }

        if (course != null) {
            tvCourseCode.setText("Course Code: " + course.getCourseCode());
            tvCourseName.setText("Course Name: " + course.getCourseName());
            tvCredits.setText("Credits: " + course.getCredits());
            tvFaculty.setText("Faculty: " + course.getFacultyName());
        }
    }
}