package com.example.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etPassword, etConfirmPassword;
    private RadioGroup rgGender;
    private CheckBox cbNewsletter;
    private Button btnSignUp, btnGoToLogin;
    private SharedPreferences userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        etFullName = findViewById(R.id.et_fullname);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        rgGender = findViewById(R.id.rg_gender);
        cbNewsletter = findViewById(R.id.cb_newsletter);
        btnSignUp = findViewById(R.id.btn_signup);
        btnGoToLogin = findViewById(R.id.btn_go_to_login);
    }

    private void setupClickListeners() {
        btnSignUp.setOnClickListener(v -> performSignUp());
        btnGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish(); 
        });
    }

    private void performSignUp() {
        clearErrors();

        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            RadioButton selectedGender = findViewById(selectedGenderId);
            gender = selectedGender.getText().toString();
        }
        boolean newsletter = cbNewsletter.isChecked();

        // Validation
        if (TextUtils.isEmpty(fullName)) {
            etFullName.setError("Full name is required");
            etFullName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            etPhone.setError("Phone number is required");
            etPhone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save user info
        SharedPreferences.Editor editor = userPrefs.edit();
        int nextId = userPrefs.getInt("next_user_id", 1);

        editor.putString("user_" + nextId + "_name", fullName);
        editor.putString("user_" + nextId + "_email", email);
        editor.putString("user_" + nextId + "_phone", phone);
        editor.putString("user_" + nextId + "_gender", gender);
        editor.putString("user_" + nextId + "_password", password);
        editor.putBoolean("user_" + nextId + "_newsletter", newsletter);
        editor.putInt("next_user_id", nextId + 1);
        editor.apply();

        Toast.makeText(this, "Registration successful! Please log in.", Toast.LENGTH_SHORT).show();

        // Go back to login
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void clearErrors() {
        etFullName.setError(null);
        etEmail.setError(null);
        etPhone.setError(null);
        etPassword.setError(null);
        etConfirmPassword.setError(null);
    }
}
