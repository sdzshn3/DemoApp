package com.sdzshn3.android.demoapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sdzshn3.android.demoapp.Database.User.User;
import com.sdzshn3.android.demoapp.Database.User.UserViewModel;
import com.sdzshn3.android.demoapp.Home.Activities.MainActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEdit;
    EditText passwordEdit;
    ImageView logoImage;
    Button loginButton;

    private UserViewModel userViewModel;
    String username;
    String password;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefManager = new PrefManager(LoginActivity.this);
        if (prefManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }

        usernameEdit = findViewById(R.id.username_edit);
        passwordEdit = findViewById(R.id.password_edit);
        logoImage = findViewById(R.id.logo_image);
        loginButton = findViewById(R.id.login_button);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null) {
                    try {
                        if (users.get(0) != null) {
                            username = users.get(0).getUsername();
                            password = users.get(0).getPassword();
                        }
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEdit.getText().toString().trim().equals(username) &&
                        passwordEdit.getText().toString().trim().equals(password)) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    prefManager.setIsLoggedIn(true);
                    finish();
                } else {
                    passwordEdit.setText("");
                    passwordEdit.setError("Username or password is incorrect");
                }
            }
        });
    }
}
