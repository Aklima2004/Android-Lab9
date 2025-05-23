package com.example.lab09.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab09.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private Button buttonSignUp, buttonLogin;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonSignUp.setOnClickListener(view -> {
            String username = editTextUsername.getText().toString().trim();
            if (!username.isEmpty()) {
                saveUsername(username);
                Toast.makeText(this, "Успешная регистрация!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Пожалуйста, введите имя пользователя", Toast.LENGTH_SHORT).show();
            }
        });

        buttonLogin.setOnClickListener(view -> {
            String inputName = editTextUsername.getText().toString().trim();
            String savedName = getUsername();
            if (savedName != null && savedName.equals(inputName)) {
                Toast.makeText(this, "Успешный вход!", Toast.LENGTH_SHORT).show();
                navigateToHome();
            } else {
                Toast.makeText(this, "Такого пользователя нет или неправильное имя", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUsername(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    private String getUsername() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
