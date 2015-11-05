package com.epicodus.postmatesclone.models;


public class User {
    private String mName;
    private String mPassword;

    public User() {
        super();
    }

    public User(String name, String password) {
        super();
        mName = name;
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public String getPassword() {
        return mPassword;
    }

    public static User find(String username, String password) {
        //query parse
        return null;
    }
}
