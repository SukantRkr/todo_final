package com.example.todo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText txtUserName = findViewById(R.id.login_activity_username);
        EditText txtPassword = findViewById(R.id.login_activity_password);
        Button btnLogin = findViewById(R.id.login_activity_btn_login);
        Button btnCance = findViewById(R.id.login_activity_btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUserName.getText().toString().equals("admin") &&
                        txtPassword.getText().toString().equals("admin123")){
                    // store login token into shared preferences
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPreferences.edit();
                    spEditor.putString("loginToken", "Loggedin");
                    spEditor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}