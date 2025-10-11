package com.example.assignment3.model;



public class Faculty {
    private long id;
    private String facultyName;
    private String deanName;

    // Constructors, getters and setters
    public Faculty() {}

    public Faculty(long id, String facultyName, String deanName) {
        this.id = id;
        this.facultyName = facultyName;
        this.deanName = deanName;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
    public String getDeanName() { return deanName; }
    public void setDeanName(String deanName) { this.deanName = deanName; }
}
