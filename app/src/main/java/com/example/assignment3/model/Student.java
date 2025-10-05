package com.example.assignment3.model;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String studentId;
    private String email;
    private String phone;
    private String gender;

    // Constructor
    public Student(String name, String studentId, String email, String phone, String gender) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
