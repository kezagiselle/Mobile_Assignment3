package com.example.assignment3.model;

public class CourseWithFaculty {
    private long id;
    private String courseCode;
    private String courseName;
    private int credits;
    private long facultyId;
    private String facultyName;

    // Constructors, getters and setters
    public CourseWithFaculty() {}

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public long getFacultyId() { return facultyId; }
    public void setFacultyId(long facultyId) { this.facultyId = facultyId; }
    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
}
