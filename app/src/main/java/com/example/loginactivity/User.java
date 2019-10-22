package com.example.loginactivity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private String password;
    private String data;

    public User(String line1, String line2, String line3) {
        username = line1;
        password = line2;
        data = line3;
    }
    public User(String line1, String line2) {
        username = line1;
        password = line2;
        data = "NEW USER";
    }

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        data = in.readString();
    }

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
