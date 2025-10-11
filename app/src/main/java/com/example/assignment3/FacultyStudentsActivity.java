
package com.example.assignment3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment3.StudentWithFaculty;
import java.util.List;

public class FacultyStudentsActivity extends AppCompatActivity {

    private ListView listViewStudents;
    private TextView tvTitle;
    private MyDatabaseHelper dbHelper;
    private long facultyId;
    private String facultyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_students);

        dbHelper = new MyDatabaseHelper(this);

        listViewStudents = findViewById(R.id.listViewStudents);
        tvTitle = findViewById(R.id.tvTitle);

        facultyId = getIntent().getLongExtra("FACULTY_ID", -1);
        facultyName = getIntent().getStringExtra("FACULTY_NAME");

        tvTitle.setText("Students in " + facultyName);
        loadStudents();
    }

    private void loadStudents() {
        List<StudentWithFaculty> students = dbHelper.getStudentsByFaculty(facultyId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1) {
            @Override
            public String getItem(int position) {
                StudentWithFaculty student = students.get(position);
                return student.getNames() + " (" + student.getStudentId() + ")\n" +
                        student.getEmail() + " | " + student.getPhone();
            }
        };
        listViewStudents.setAdapter(adapter);
    }
}