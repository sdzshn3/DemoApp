package com.sdzshn3.android.demoapp.UserDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sdzshn3.android.demoapp.Database.User.User;
import com.sdzshn3.android.demoapp.Database.User.UserViewModel;
import com.sdzshn3.android.demoapp.R;

import java.util.List;

public class UserDetailsActivity extends AppCompatActivity {

    Button saveButton;
    EditText firstNameEdit;
    EditText middleNameEdit;
    EditText lastNameEdit;
    EditText emailEdit;
    EditText phoneEdit;
    EditText locationEdit;
    EditText collegeEdit;
    int id;
    UserViewModel userViewModel;
    String status;
    String password;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        saveButton = findViewById(R.id.save_button);
        firstNameEdit = findViewById(R.id.first_name);
        middleNameEdit = findViewById(R.id.middle_name);
        lastNameEdit = findViewById(R.id.last_name);
        emailEdit = findViewById(R.id.email);
        phoneEdit = findViewById(R.id.phone);
        locationEdit = findViewById(R.id.location);
        collegeEdit = findViewById(R.id.college);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                id = users.get(0).getId();
                firstNameEdit.setText(users.get(0).getFirst_name());
                middleNameEdit.setText(users.get(0).getMiddle_name());
                lastNameEdit.setText(users.get(0).getLast_name());
                emailEdit.setText(users.get(0).getEmail());
                phoneEdit.setText(users.get(0).getPhone());
                locationEdit.setText(users.get(0).getLocation());
                collegeEdit.setText(users.get(0).getCollege());
                status = users.get(0).getStatus();
                password = users.get(0).getPassword();
                userName = users.get(0).getUsername();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEdit.getText().toString().trim();
                String middleName = middleNameEdit.getText().toString().trim();
                String lastName = lastNameEdit.getText().toString().trim();
                String email = emailEdit.getText().toString().trim();
                String phone = phoneEdit.getText().toString().trim();
                String location = locationEdit.getText().toString().trim();
                String college = collegeEdit.getText().toString().trim();

                User user = new User(firstName, lastName, middleName, phone, email, college, location, status, userName, password);
                user.setId(id);
                userViewModel.update(user);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
