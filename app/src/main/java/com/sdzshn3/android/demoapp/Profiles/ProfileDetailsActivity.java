package com.sdzshn3.android.demoapp.Profiles;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdzshn3.android.demoapp.Database.Profile.Profile;
import com.sdzshn3.android.demoapp.Database.Profile.ProfilesViewModel;
import com.sdzshn3.android.demoapp.R;

import java.util.List;

public class ProfileDetailsActivity extends AppCompatActivity {

    TextView nameText;
    TextView descriptionText;
    Button saveButton;
    int position;
    boolean isEdit;
    ProfilesViewModel profilesViewModel;
    String name;
    String description;
    String option1;
    int documentId;
    int userId;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        Intent intent = getIntent();
        position = intent.getIntExtra(ProfilesFragment.POSITION, 0);
        isEdit = intent.getBooleanExtra(ProfilesFragment.IS_EDIT, false);
        setTitle("");
        nameText = findViewById(R.id.text_1);
        descriptionText = findViewById(R.id.text_2);
        saveButton = findViewById(R.id.save_button);

        profilesViewModel = ViewModelProviders.of(this).get(ProfilesViewModel.class);
        if (isEdit) {
            profilesViewModel.getAllProfiles().observe(this, new Observer<List<Profile>>() {
                @Override
                public void onChanged(List<Profile> profiles) {
                    id = profiles.get(position).getId();
                    nameText.setText(profiles.get(position).getName());
                    if (isEdit) {
                        setTitle(profiles.get(position).getName());
                    }
                    descriptionText.setText(profiles.get(position).getDescription());
                    name = profiles.get(position).getName();
                    description = profiles.get(position).getDescription();
                    option1 = profiles.get(position).getOption1();
                    documentId = profiles.get(position).getDocument_id();
                    userId = profiles.get(position).getUser_id();
                }
            });
        } else {
            setTitle("New Profile");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameText.getText().toString().trim();
                description = descriptionText.getText().toString().trim();
                Profile profile = new Profile(name, description, option1, documentId, userId);
                if (isEdit) {
                    profile.setId(id);
                    profilesViewModel.update(profile);
                    finish();
                } else {
                    profilesViewModel.insert(profile);
                    finish();
                }
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