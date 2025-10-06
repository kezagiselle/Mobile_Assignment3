package com.example.assignment3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.model.Student;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<Student> studentList;
    LocalStorageHelper storage;  // Use local storage instead of SQLite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.userListView);
        storage = new LocalStorageHelper(this);

        // Fetch students from SharedPreferences
        studentList = storage.getAllUsers();  // Make sure LocalStorageHelper returns ArrayList<Student>

        // Convert to String for display (ID + Name only)
        ArrayList<String> displayList = new ArrayList<>();
        for (Student s : studentList) {
            displayList.add("ID: " + s.getStudentId() + " - " + s.getName());
        }

        // Bind data to ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);

        // Handle item click → open options
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Student selectedStudent = studentList.get(position);
            showOptions(selectedStudent);
        });
    }

    // Show options dialog (Call or Email)
    private void showOptions(Student student) {
        String[] options = {"Call " + student.getPhone(), "Email " + student.getEmail()};
        new AlertDialog.Builder(this)
                .setTitle("Choose Action")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        // Implicit intent for phone call
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + student.getPhone()));
                        startActivity(callIntent);
                    } else {
                        // Implicit intent for email
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:" + student.getEmail()));
                        startActivity(emailIntent);
                    }
                })
                .show();
    }
}
