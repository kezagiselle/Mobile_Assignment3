// MyDatabaseHelper.java
package com.example.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment3.model.CourseWithFaculty;
import com.example.assignment3.model.Faculty;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // Database constants
    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 2;

    // Table names
    public static final String TABLE_FACULTIES = "faculties";
    public static final String TABLE_STUDENTS = "students";
    public static final String TABLE_COURSES = "courses";

    // Common column
    public static final String COLUMN_ID = "_id";

    // Faculties table columns
    public static final String COLUMN_FACULTY_NAME = "faculty_name";
    public static final String COLUMN_DEAN_NAME = "dean_name";

    // Students table columns
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_NAMES = "names";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_FACULTY_ID = "faculty_id";

    // Courses table columns
    public static final String COLUMN_COURSE_CODE = "course_code";
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_CREDITS = "credits";

    // Create table SQL statements
    private static final String CREATE_TABLE_FACULTIES =
            "CREATE TABLE " + TABLE_FACULTIES + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FACULTY_NAME + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_DEAN_NAME + " TEXT NOT NULL" +
                    ");";

    private static final String CREATE_TABLE_STUDENTS =
            "CREATE TABLE " + TABLE_STUDENTS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_STUDENT_ID + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_NAMES + " TEXT NOT NULL, " +
                    COLUMN_GENDER + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL, " +
                    COLUMN_PHONE + " TEXT NOT NULL, " +
                    COLUMN_FACULTY_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + COLUMN_FACULTY_ID + ") REFERENCES " +
                    TABLE_FACULTIES + "(" + COLUMN_ID + ")" +
                    ");";

    private static final String CREATE_TABLE_COURSES =
            "CREATE TABLE " + TABLE_COURSES + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_COURSE_CODE + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_COURSE_NAME + " TEXT NOT NULL, " +
                    COLUMN_CREDITS + " INTEGER NOT NULL, " +
                    COLUMN_FACULTY_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + COLUMN_FACULTY_ID + ") REFERENCES " +
                    TABLE_FACULTIES + "(" + COLUMN_ID + ")" +
                    ");";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FACULTIES);
        db.execSQL(CREATE_TABLE_STUDENTS);
        db.execSQL(CREATE_TABLE_COURSES);

        // Insert sample data
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACULTIES);
        onCreate(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Insert sample faculties
        ContentValues fac1 = new ContentValues();
        fac1.put(COLUMN_FACULTY_NAME, "Computer Science");
        fac1.put(COLUMN_DEAN_NAME, "Dr. Smith");
        db.insert(TABLE_FACULTIES, null, fac1);

        ContentValues fac2 = new ContentValues();
        fac2.put(COLUMN_FACULTY_NAME, "Business Administration");
        fac2.put(COLUMN_DEAN_NAME, "Dr. Johnson");
        db.insert(TABLE_FACULTIES, null, fac2);
    }

    // FACULTY CRUD OPERATIONS
    public long addFaculty(String facultyName, String deanName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FACULTY_NAME, facultyName);
        values.put(COLUMN_DEAN_NAME, deanName);

        long result = db.insert(TABLE_FACULTIES, null, values);
        db.close();
        return result;
    }

    public List<Faculty> getAllFaculties() {
        List<Faculty> faculties = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FACULTIES,
                new String[]{COLUMN_ID, COLUMN_FACULTY_NAME, COLUMN_DEAN_NAME},
                null, null, null, null, COLUMN_FACULTY_NAME + " ASC");

        if (cursor.moveToFirst()) {
            do {
                Faculty faculty = new Faculty();
                faculty.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                faculty.setFacultyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_NAME)));
                faculty.setDeanName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEAN_NAME)));
                faculties.add(faculty);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return faculties;
    }

    public Faculty getFacultyById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FACULTIES,
                new String[]{COLUMN_ID, COLUMN_FACULTY_NAME, COLUMN_DEAN_NAME},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        Faculty faculty = null;
        if (cursor != null && cursor.moveToFirst()) {
            faculty = new Faculty();
            faculty.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            faculty.setFacultyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_NAME)));
            faculty.setDeanName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEAN_NAME)));
            cursor.close();
        }
        db.close();
        return faculty;
    }

    // STUDENT CRUD OPERATIONS
    public long addStudent(String studentId, String names, String gender,
                           String email, String phone, long facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_ID, studentId);
        values.put(COLUMN_NAMES, names);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_FACULTY_ID, facultyId);

        long result = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return result;
    }

    public List<StudentWithFaculty> getAllStudentsWithFaculty() {
        List<StudentWithFaculty> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT s.*, f." + COLUMN_FACULTY_NAME +
                " FROM " + TABLE_STUDENTS + " s " +
                " INNER JOIN " + TABLE_FACULTIES + " f ON s." + COLUMN_FACULTY_ID + " = f." + COLUMN_ID +
                " ORDER BY s." + COLUMN_NAMES + " ASC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                StudentWithFaculty student = new StudentWithFaculty();
                student.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                student.setStudentId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_ID)));
                student.setNames(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMES)));
                student.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)));
                student.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
                student.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
                student.setFacultyId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_ID)));
                student.setFacultyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_NAME)));
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }

    public List<StudentWithFaculty> getStudentsByFaculty(long facultyId) {
        List<StudentWithFaculty> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT s.*, f." + COLUMN_FACULTY_NAME +
                " FROM " + TABLE_STUDENTS + " s " +
                " INNER JOIN " + TABLE_FACULTIES + " f ON s." + COLUMN_FACULTY_ID + " = f." + COLUMN_ID +
                " WHERE s." + COLUMN_FACULTY_ID + " = ?" +
                " ORDER BY s." + COLUMN_NAMES + " ASC";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(facultyId)});

        if (cursor.moveToFirst()) {
            do {
                StudentWithFaculty student = new StudentWithFaculty();
                student.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                student.setStudentId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_ID)));
                student.setNames(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMES)));
                student.setGender(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)));
                student.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
                student.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)));
                student.setFacultyId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_ID)));
                student.setFacultyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_NAME)));
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }

    // COURSE CRUD OPERATIONS
    public long addCourse(String courseCode, String courseName, int credits, long facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_CODE, courseCode);
        values.put(COLUMN_COURSE_NAME, courseName);
        values.put(COLUMN_CREDITS, credits);
        values.put(COLUMN_FACULTY_ID, facultyId);

        long result = db.insert(TABLE_COURSES, null, values);
        db.close();
        return result;
    }

    public List<CourseWithFaculty> getAllCoursesWithFaculty() {
        List<CourseWithFaculty> courses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT c.*, f." + COLUMN_FACULTY_NAME +
                " FROM " + TABLE_COURSES + " c " +
                " INNER JOIN " + TABLE_FACULTIES + " f ON c." + COLUMN_FACULTY_ID + " = f." + COLUMN_ID +
                " ORDER BY c." + COLUMN_COURSE_NAME + " ASC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                CourseWithFaculty course = new CourseWithFaculty();
                course.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                course.setCourseCode(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_CODE)));
                course.setCourseName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_NAME)));
                course.setCredits(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CREDITS)));
                course.setFacultyId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_ID)));
                course.setFacultyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_NAME)));
                courses.add(course);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return courses;
    }

    public List<CourseWithFaculty> getCoursesByFaculty(long facultyId) {
        List<CourseWithFaculty> courses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT c.*, f." + COLUMN_FACULTY_NAME +
                " FROM " + TABLE_COURSES + " c " +
                " INNER JOIN " + TABLE_FACULTIES + " f ON c." + COLUMN_FACULTY_ID + " = f." + COLUMN_ID +
                " WHERE c." + COLUMN_FACULTY_ID + " = ?" +
                " ORDER BY c." + COLUMN_COURSE_NAME + " ASC";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(facultyId)});

        if (cursor.moveToFirst()) {
            do {
                CourseWithFaculty course = new CourseWithFaculty();
                course.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                course.setCourseCode(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_CODE)));
                course.setCourseName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_NAME)));
                course.setCredits(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CREDITS)));
                course.setFacultyId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_ID)));
                course.setFacultyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_NAME)));
                courses.add(course);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return courses;
    }

    public CourseWithFaculty getCourseById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT c.*, f." + COLUMN_FACULTY_NAME +
                " FROM " + TABLE_COURSES + " c " +
                " INNER JOIN " + TABLE_FACULTIES + " f ON c." + COLUMN_FACULTY_ID + " = f." + COLUMN_ID +
                " WHERE c." + COLUMN_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        CourseWithFaculty course = null;
        if (cursor != null && cursor.moveToFirst()) {
            course = new CourseWithFaculty();
            course.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            course.setCourseCode(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_CODE)));
            course.setCourseName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_NAME)));
            course.setCredits(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CREDITS)));
            course.setFacultyId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_ID)));
            course.setFacultyName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FACULTY_NAME)));
            cursor.close();
        }
        db.close();
        return course;
    }

    // Update student
    public boolean updateStudent(long id, String studentId, String names, String gender,
                                 String email, String phone, long facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_ID, studentId);
        values.put(COLUMN_NAMES, names);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_FACULTY_ID, facultyId);

        int result = db.update(TABLE_STUDENTS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    // Delete student
    public boolean deleteStudent(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_STUDENTS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }


}