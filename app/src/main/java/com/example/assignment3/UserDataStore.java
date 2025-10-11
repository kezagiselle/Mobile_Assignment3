package com.example.assignment3;

import com.example.assignment3.model.Student;

import java.util.ArrayList;

public class UserDataStore {
    private static ArrayList<Student> users = new ArrayList<>();
    private static int idCounter = 1;

    public static void addUser(Student user) {
        users.add(user);
    }

    public static ArrayList<Student> getUsers() {
        return  users;
    }

    public static int getNextId() {
        return idCounter++;
    }
}
