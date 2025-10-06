package com.example.assignment3;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.assignment3.model.Student;

import java.util.ArrayList;

public class LocalStorageHelper {

    private static final String PREF_NAME = "student_pref";
    private static final String KEY_COUNT = "student_count";

    private SharedPreferences prefs;

    public LocalStorageHelper(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // Save a new student
    public void addStudent(Student student) {
        int count = prefs.getInt(KEY_COUNT, 0);  // Number of students saved
        count++;  // New student ID in storage

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("student_" + count + "_name", student.getName());
        editor.putString("student_" + count + "_id", student.getStudentId());
        editor.putString("student_" + count + "_email", student.getEmail());
        editor.putString("student_" + count + "_phone", student.getPhone());
        editor.putString("student_" + count + "_gender", student.getGender());
        editor.putInt(KEY_COUNT, count);  // Update count

        editor.apply();
    }

    // Retrieve all students
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        int count = prefs.getInt(KEY_COUNT, 0);

        for (int i = 1; i <= count; i++) {
            String name = prefs.getString("student_" + i + "_name", "");
            String studentId = prefs.getString("student_" + i + "_id", "");
            String email = prefs.getString("student_" + i + "_email", "");
            String phone = prefs.getString("student_" + i + "_phone", "");
            String gender = prefs.getString("student_" + i + "_gender", "");

            if (!name.isEmpty()) {
                students.add(new Student(name, studentId, email, phone, gender));
            }
        }

        return students;
    }
}
