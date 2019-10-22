package com.example.loginactivity;

import android.app.Application;

import java.util.ArrayList;

public class UserList extends Application {
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
