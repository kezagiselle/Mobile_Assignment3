package com.example.assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assignment3.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_list_item_student, parent, false);
        }

        TextView textStudentId = convertView.findViewById(R.id.textStudentId);
        TextView textStudentName = convertView.findViewById(R.id.textStudentName);

        textStudentId.setText("ID: " + student.getStudentId());
        textStudentName.setText("Name: " + student.getName());

        return convertView;
    }
}