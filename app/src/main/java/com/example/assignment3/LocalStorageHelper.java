package com.example.assignment3;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.assignment3.model.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LocalStorageHelper {
    private static final String PREF_NAME = "student_pref";
    private static final String KEY_USERS = "users";

    private SharedPreferences prefs;
    private Gson gson;

    public LocalStorageHelper(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Save a new user
    public void addUser(User user) {
        ArrayList<User> users = getAllUsers();
        users.add(user);

        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(users);
        editor.putString(KEY_USERS, json);
        editor.apply();
    }

    // Retrieve all users
    public ArrayList<Student> getAllUsers() {
        String json = prefs.getString(KEY_USERS, null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();

        if (json != null) {
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }
}
