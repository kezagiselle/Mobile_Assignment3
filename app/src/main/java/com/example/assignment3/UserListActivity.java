package com.example.assignment3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<User> userList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView = findViewById(R.id.userListView);
        db = new DatabaseHelper(this);

        // Fetch from DB
        userList = db.getAllUsers();

        // Convert to String for display (ID + Name only)
        ArrayList<String> displayList = new ArrayList<>();
        for (User u : userList) {
            displayList.add("ID: " + u.getId() + " - " + u.getName());
        }

        // Bind data to ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);

        // Handle item click
        listView.setOnItemClickListener((parent, view, position, id) -> {
            User selectedUser = userList.get(position);
            showOptions(selectedUser);
        });
    }

    // Show options dialog (Call or Email)
    private void showOptions(User user) {
        String[] options = {"Call " + user.getPhone(), "Email " + user.getEmail()};
        new AlertDialog.Builder(this)
                .setTitle("Choose Action")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        // Implicit intent for phone call
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + user.getPhone()));
                        startActivity(callIntent);
                    } else {
                        // Implicit intent for email
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:" + user.getEmail()));
                        startActivity(emailIntent);
                    }
                })
                .show();
    }
}
