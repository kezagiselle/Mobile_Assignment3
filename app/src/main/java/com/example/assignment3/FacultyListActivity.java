// FacultyListActivity.java
package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment3.model.Faculty;
import java.util.List;

public class FacultyListActivity extends AppCompatActivity {

    private ListView listViewFaculties;
    private Button btnAddFaculty, btnViewStudents, btnViewCourses;
    private MyDatabaseHelper dbHelper;
    private List<Faculty> faculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_list);

        dbHelper = new MyDatabaseHelper(this);

        listViewFaculties = findViewById(R.id.listViewFaculties);
        btnAddFaculty = findViewById(R.id.btnAddFaculty);
        btnViewStudents = findViewById(R.id.btnViewStudents);
        btnViewCourses = findViewById(R.id.btnViewCourses);

        loadFaculties();

        btnAddFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyListActivity.this, AddFacultyActivity.class);
                startActivity(intent);
            }
        });

        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyListActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });

        btnViewCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyListActivity.this, CourseListActivity.class);
                startActivity(intent);
            }
        });

        listViewFaculties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Faculty selectedFaculty = faculties.get(position);
                Intent intent = new Intent(FacultyListActivity.this, FacultyDetailActivity.class);
                intent.putExtra("FACULTY_ID", selectedFaculty.getId());
                intent.putExtra("FACULTY_NAME", selectedFaculty.getFacultyName());
                startActivity(intent);
            }
        });
    }

    private void loadFaculties() {
        faculties = dbHelper.getAllFaculties();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1) {
            @Override
            public String getItem(int position) {
                Faculty faculty = faculties.get(position);
                return faculty.getFacultyName() + " - Dean: " + faculty.getDeanName();
            }
        };
        listViewFaculties.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFaculties();
    }
}