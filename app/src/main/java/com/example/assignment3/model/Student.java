package com.example.assignment3.model;

public class Student {
    private String userId;
    private String name;
    private String password;
    private String gender;
    private String email;
    private String phone;
    private  boolean newsletter;

    public Student(String userId, String name, String password, String gender, String email, String phone, boolean newsletter) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.newsletter = newsletter;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public  boolean isNewsletter() { return newsletter;}

    // Setters
    public void setStudentId(String studentId) { this.userId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setGender(String gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setNewsletter(boolean newsletter) { this.newsletter = newsletter;}


}