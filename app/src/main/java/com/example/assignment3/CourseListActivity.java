// CourseListActivity.java
package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment3.model.CourseWithFaculty;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private ListView listViewCourses;
    private Button btnAddCourse, btnBackToFaculties;
    private MyDatabaseHelper dbHelper;
    private List<CourseWithFaculty> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        dbHelper = new MyDatabaseHelper(this);

        listViewCourses = findViewById(R.id.listViewCourses);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnBackToFaculties = findViewById(R.id.btnBackToFaculties);

        loadCourses();

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseListActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });

        btnBackToFaculties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseListActivity.this, FacultyListActivity.class);
                startActivity(intent);
            }
        });

        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CourseWithFaculty selectedCourse = courses.get(position);
                Intent intent = new Intent(CourseListActivity.this, CourseDetailActivity.class);
                intent.putExtra("COURSE_ID", selectedCourse.getId());
                startActivity(intent);
            }
        });
    }

    private void loadCourses() {
        courses = dbHelper.getAllCoursesWithFaculty();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1) {
            @Override
            public String getItem(int position) {
                CourseWithFaculty course = courses.get(position);
                return course.getCourseCode() + " - " + course.getCourseName() +
                        " (" + course.getCredits() + " credits) - " + course.getFacultyName();
            }
        };
        listViewCourses.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCourses();
    }

    public static class StudentWithFaculty {
        private long id;
        private String studentId;
        private String names;
        private String gender;
        private String email;
        private String phone;
        private long facultyId;
        private String facultyName;

        // Constructors, getters and setters
        public StudentWithFaculty() {}

        // Getters and setters
        public long getId() { return id; }
        public void setId(long id) { this.id = id; }
        public String getStudentId() { return studentId; }
        public void setStudentId(String studentId) { this.studentId = studentId; }
        public String getNames() { return names; }
        public void setNames(String names) { this.names = names; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public long getFacultyId() { return facultyId; }
        public void setFacultyId(long facultyId) { this.facultyId = facultyId; }
        public String getFacultyName() { return facultyName; }
        public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
    }
}