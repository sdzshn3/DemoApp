package com.sdzshn3.android.demoapp.Database.User;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String first_name;

    private String last_name;

    private String middle_name;

    private String phone;

    private String email;

    private String college;

    private String location;

    private String status;

    private String username;

    private String password;

    public User(String first_name, String last_name,
                String middle_name, String phone,
                String email, String college, String location,
                String status, String username, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.phone = phone;
        this.email = email;
        this.college = college;
        this.location = location;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCollege() {
        return college;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
