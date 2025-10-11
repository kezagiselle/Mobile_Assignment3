// StudentDetailActivity.java
package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assignment3.StudentWithFaculty;
import java.util.List;

public class StudentDetailActivity extends AppCompatActivity {

    private TextView tvStudentId, tvNames, tvGender, tvEmail, tvPhone, tvFaculty;
    private Button btnBack;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        dbHelper = new MyDatabaseHelper(this);

        tvStudentId = findViewById(R.id.tvStudentId);
        tvNames = findViewById(R.id.tvNames);
        tvGender = findViewById(R.id.tvGender);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvFaculty = findViewById(R.id.tvFaculty);
        btnBack = findViewById(R.id.btnBack);

        long studentId = getIntent().getLongExtra("STUDENT_ID", -1);
        loadStudentDetails(studentId);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadStudentDetails(long studentId) {
        List<StudentWithFaculty> allStudents = dbHelper.getAllStudentsWithFaculty();
        StudentWithFaculty student = null;

        for (StudentWithFaculty s : allStudents) {
            if (s.getId() == studentId) {
                student = s;
                break;
            }
        }

        if (student != null) {
            tvStudentId.setText("Student ID: " + student.getStudentId());
            tvNames.setText("Name: " + student.getNames());
            tvGender.setText("Gender: " + student.getGender());
            tvEmail.setText("Email: " + student.getEmail());
            tvPhone.setText("Phone: " + student.getPhone());
            tvFaculty.setText("Faculty: " + student.getFacultyName());
        }
    }
}