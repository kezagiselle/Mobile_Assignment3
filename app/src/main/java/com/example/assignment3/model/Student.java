package com.example.assignment3.model;

public class Student {
    private String studentId;
    private String name;
    private String password;
    private String gender;
    private String email;
    private String phone;

    public Student(String studentId, String name, String password, String gender, String email, String phone) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // Setters
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setGender(String gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
}