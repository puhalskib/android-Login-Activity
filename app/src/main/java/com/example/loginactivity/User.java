package com.example.loginactivity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    //private string data
    private String username;
    private String password;
    private String data;

    //initialize user
    public User(String u, String p, String d) {
        username = u;
        password = p;
        data = d;
    }
    //sets
    public void setData(String d) {
        this.data = d;
    }

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        data = in.readString();
    }
    //parcelable functions
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //gets
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(data);
    }
}
