// StudentWithFaculty.java
package com.example.assignment3; // Main package

public class StudentWithFaculty {
    private long id;
    private String studentId;
    private String names;
    private String gender;
    private String email;
    private String phone;
    private long facultyId;
    private String facultyName;

    // Constructors, getters and setters
    public StudentWithFaculty() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getNames() { return names; }
    public void setNames(String names) { this.names = names; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public long getFacultyId() { return facultyId; }
    public void setFacultyId(long facultyId) { this.facultyId = facultyId; }
    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
}